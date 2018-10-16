# Transbank SDK Android Onepay

## Instalación

- Descarga el SDK desde desde [la página de releases](https://github.com/TransbankDevelopers/transbank-sdk-android-onepay/releases). Para comenzar el desarrollo puedes usar la versión para ambiente de Test (y luego deberás compilar contra la versión de Producción para tu app productiva)
- Agrega el archivo `.aar` creándolo como un módulo en tu proyecto Android. Para esto sigue los siguientes pasos: 
  - En Android Studio ve a `File -> New -> New Module -> Import .jar/.aar` e importa el archivo `.aar` que descargaste.
  - Luego en el `build.gradle` de tu proyecto (el que pertenece al módulo "app") agrega lo siguiente dentro de la sección `dependencies`: 

    ```
    implementation project(':one_pay_sdk_tbkqa')
    ```

    El nombre del proyecto depende del nombre del archivo que hayas importado (o el nombre que le hayas puesto manualmente al subproyecto al importarlo. No olvides los dos puntos antes del nombre del proyecto.

- Luego haz un Clean Build y estás listo para usar el SDK. Ya podrás importar las clases bajo `cl.ionix.tbk_ewallet_sdk_android`

## Documentación 

Puedes encontrar toda la documentación de cómo usar este SDK en el sitio https://www.transbankdevelopers.cl.

La documentación relevante para usar este SDK es:

- Documentación general sobre el producto
  [Onepay](https://www.transbankdevelopers.cl/producto/onepay).
- Documentación sobre [ambientes, deberes del comercio, puesta en producción,
  etc](https://www.transbankdevelopers.cl/documentacion/como_empezar#ambientes).
- Primeros pasos con [Onepay](https://www.transbankdevelopers.cl/documentacion/onepay).
- Referencia detallada sobre [Onepay](https://www.transbankdevelopers.cl/referencia/onepay).

## Información para contribuir y desarrollar este SDK


[TODO]