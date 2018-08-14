# Transbank SDK Android Onepay

## Instalación

- Descarga el SDK desde desde [la página de releases](https://github.com/TransbankDevelopers/transbank-sdk-android-onepay/releases). Para comenzar el desarrollo puedes usar la versión para ambiente de Test (y luego deberás compilar contra la versión de Producción para tu app productiva)
- Agrega el archivo `.aar` [creándolo como un módulo en tu proyecto Android](https://medium.com/@notestomyself/how-to-include-external-aar-file-using-gradle-6604b378e808).

## Modo de uso  

### Detección e Instalación de app Onepay

Para detectar si Onepay está disponible e invitar al usuario a instalarlo:

```java
import cl.ionix.tbk_ewallet_sdk_android.Error;
import cl.ionix.tbk_ewallet_sdk_android.OnePay;
import cl.ionix.tbk_ewallet_sdk_android.callback.OnePayCallback;
//...

Onepay onepay = new OnePay(this);
if (onepay.isOnePayInstalled()) {
  // Todo OK, sigue adelante
} else {
  // Ofrece al usuario si quiere instalar Onepay. 
  // En caso que esté de acuerdo, usa lo siguiente para iniciar la instalación:
  onepay.installOnePay();
}
```

### Iniciar una transacción

En tu backend debes crear una transacción indicando el `channel` `MOBILE` y el `appSchema` que corresponda a tu app. Luego debes transmitir a tu app el `occ` obtenido en el backend. Con ese dato puedes iniciar el pago usando la clase `OnePay` desde cualquier activity:

```java
import cl.ionix.tbk_ewallet_sdk_android.Error;
import cl.ionix.tbk_ewallet_sdk_android.OnePay;
import cl.ionix.tbk_ewallet_sdk_android.callback.OnePayCallback;
//...

Onepay onepay = new OnePay(this);
onepay.initPayment("occ", new OnePayCallback() {
    @Override
    public void failure(Error error, String s) {
        switch (error) {
            case Error.INVALID_OCC:
                // Algo anda mal con el occ que obtuviste desde el backend
                // Debes reintentar obtener el occ o abortar
                break;
            case Error.ONE_PAY_NOT_INSTALLED:
                // Onepay no está instalado.
                // Debes abortar o pedir al usuario instalar Onepay (y luego reintentar initPayment)
                break;
            default:            
                Log.e(TAG, "Error inesperado al iniciar pago Onepay " + error.toString() + ":" + s);
                // Aborta o reintenta 
        }
    }
});
```

Si todo funciona OK, el control pasará a la app Onepay donde el usuario podrá autorizar la transacción.

### Recibir el callback después que el usuario

Una vez se complete el pago, Onepay redigirá el control de regreso a tu app mediante el `appSchema` indicado en el backend al crear la transacción. Tu debes registrar un `Activity` que responda a la URL registrada en dicho `appSchema`. Para eso debes configurar un intent filter a tu activity. Por ejemplo, si tu `appSchema` fuera `mi-app://mi-app/onepay-result`:

```xml
<activity ...>
  <intent-filter>
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <data android:scheme="mi-app" android:host="mi-app" android:path="onepay-result" />
  </intent-filter>
</activity>
```

Luego en ese Activity podrás obtener el resultado de la transacción a través del Intent:

```java

Intent intent = getIntent();
String occ = intent.getStringExtra("occ");
String externalUniqueNumber = intent.getStringExtra("externalUniqueNumber");
```

Finalmente deberás enviar el `occ` y el `externalUniqueNumber` a tu backend para que confirme la transacción y te informe del resultado. 

## Nota

A diferencia de otros SDK, en Android se usa `OnePay` en lugar de `Onepay` (nombre correcto del producto) . Esto obedece a razones históricas.
