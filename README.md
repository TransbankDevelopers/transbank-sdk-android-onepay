# Transbank SDK Android Onepay

## Instalación

- Descarga el SDK desde desde [la página de releases](https://github.com/TransbankDevelopers/transbank-sdk-android-onepay/releases). El SDK sirve tanto para desarrollo como para producción.
- Descomprime el release y agrega el archivo `.aar` creándolo como un módulo en tu proyecto Android. Para esto sigue los siguientes pasos: 
  - En Android Studio ve a `File -> New -> New Module -> Import .jar/.aar` e importa el archivo `.aar` que descargaste.
  - Luego en el `build.gradle` de tu proyecto (el que pertenece al módulo "app") agrega lo siguiente dentro de la sección `dependencies`: 

    ```
    implementation project(':onepay-release')
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
### Generar una nueva versión

Para generar una nueva versión, se debe crear un PR (con un título "Prepare release X.Y.Z" con los valores que correspondan para `X`, `Y` y `Z`). Se debe seguir el estándar semver para determinar si se incrementa el valor de `X` (si hay cambios no retrocompatibles), `Y` (para mejoras retrocompatibles) o `Z` (si sólo hubo correcciones a bugs).

En ese PR deben incluirse los siguientes cambios:

1. Modificar el archivo CHANGELOG.md para incluir una nueva entrada (al comienzo) para `X.Y.Z` que explique en español los cambios.

Luego de obtener aprobación del pull request, debes mezclar a master e inmediatamente generar un release en GitHub con el tag `vX.Y.Z`. En la descripción del release debes poner lo mismo que agregaste al changelog.

Con eso Travis CI generará automáticamente una nueva versión del módulo y actualizará el Release de Github con el plugin comprimido.
