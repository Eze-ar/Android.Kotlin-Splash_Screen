# Android.Kotlin-Splash_Screen
Implementando una Splash Screen con Android Studio y Kotlin mediante la API oficial de Google. Proyecto de investigación final para el curso DESARROLLO DE APLICACIONES ANDROID de FUNDACIÓN TELEFÓNICA y su programa CONECTA EMPLEO.

***Solicitud***

Hacer una pantalla de inicio SPLASH SCREEN con una imagen propia, no usando las que ya vienen en Android Studio partiendo de la actividad vista en clase.
Hacer captura de pantalla de cada paso y armar un breve instructivo que indique como hacen la aplicación completa, desde lo que hacemos en clase hasta el agregado de la imagen propia, tema que deberán investigar ustedes.

*** Como punto opcional, quienes se animen pueden poner una animación en lugar de solo una imagen, lo que aumentara la nota final.
Se evaluará no solo el resultado final de la aplicación sino también la redacción y el armado del documento que entreguen.


***Solución propuesta***

En primer lugar, buscando tanto en la fuente oficial (https://developer.android.com/) y luego en algunos sitios especializados como en el siguiente enlace:

https://proandroiddev.com/splash-screen-in-android-3bd9552b92a5

sabemos que hay diferentes enfoques para poder realizar una Splash Screen o pantalla de inicio, que es aquella que se muestra en primer plano mientras cargue nuestra aplicación propiamente dicha. Los 3 enfoques principales son:

    1. Usando Timers
    2. Usando un Launcher Theme
    3. Usando la API oficial de Google para las Splash Screen disponible a partir de Android 12 (SDK 31)

En este trabajo usaré lo propuesto por Google, es decir usando su API. Tal como se menciona en el sitio oficial:

https://developer.android.com/develop/ui/views/launch/splash-screen?hl=es-419

A partir de Android 12, se habilita una nueva animación de inicio de apps para todas las apps cuando se ejecuta en un dispositivo con Android 12 o posterior. Esta animación incluye un movimiento de entrada a la app cuando se inicia, una pantalla de presentación que muestra el ícono de la app y una transición a la app en sí misma.

Pasos o procedimiento para crear la Splash Screen usando la API de Google:

1)  En primer lugar creamos un nuevo proyecto con NEW PROJECT

2) Elegimos la EMPTY NEW ACTIVITY o nueva actividad vacía

3) asignamos un nombre, en este caso MySplashScreen01 y click en FINISH

4) Tal como indica la documentación oficial: la biblioteca SplashScreen principal lleva la pantalla de presentación de Android 12 a todos los dispositivos a partir del nivel de API 23. Para agregarlo a tu proyecto, agrega el siguiente fragmento a tu archivo build.gradle: 

dependencies {
    implementation("androidx.core:core-splashscreen:1.0.0")
}


Vamos a PROJECT / Gradle Scripts / build.gradle.kts (module: app)

allí en dependencies agregamos lo que decíamos antes:

5) En activity_main,xml en Design reemplazamos el hello world y también completamos otras propiedades en Code

Ponemos un color para el texto con la propiedad textColor y un tamaño adecuado con textSize

Adicionalmente usando Design dispondré de un Button, ya que quiero poner un botón en el cual al hacer click se salga o termine la aplicación.

6) en themes.xml:
Defino un color de fondo para mi Splash Screen, lo cual puedo hacer de 2 maneras, o eligiendo el color y que quede allí el hexa o como terminé haciendo, definiendo un color personalizado al que llamé amarillito en colors.xml:

7) para continuar definiendo tanto el ícono para la app como el que se usará animado en la Splash Screen me incliné a crear un SVG (gráfico vectorial) con Figma. Teniendo este SVG lo trasladamos arrastrándolo con el mouse a drawable. Luego aquí mismo, botón derecho del mourse:

New / Image Asset:

y vamos al Asset Studio con en path incidaremos la ruta al archivo SVG, en este caso EzeFrame.svg

con Trim y Resize jugamos un poco hasta que nos queden los íconos como los queremos. Y así NEXT y FINISH. Nuestro ícono va a quedar definido en ic_launcher y ya podemos borrar el archivo SVG, ya que en definitiva lo que nos quedó es el XML a usar.

8) nuevamente en drawable click con botón derecho del mouse y New Resource File

como nombre de archivo elijo avd_ezeframe que será mi ícono animado de la Splash Screen.

Para este punto vale también tener presente lo que figura en la documentación oficial:

https://developer.android.com/develop/ui/views/launch/splash-screen?hl=es-419

En esa dirección se ofrece la descarga del kit de inicio de ejemplo, que muestra cómo crear, formatear y exportar una animación a un AVD e incluye lo siguiente:
    • un archivo de proyecto de Adobe After Effects de la animación.
    • Un archivo en formato XML de AVD exportado final.
Aplicando esos conceptos tenemos nuestro animated-vector y valiéndonos de la forma de visualización split donde vemos el código y el diseño tenemos por un lado a la izquierda el XML y a la derecha cómo queda la animación a la cual le podemos, PLAY mediante visualizar como nos queda.

Cómo implementé el rotation del logo:

<objectAnimator
                    android:propertyName="rotation"
                    android:duration="2500"
                    android:valueFrom="0"
                    android:valueTo="360"
                    android:valueType="floatType"
                    android:interpolator="@android:interpolator/fast_out_slow_in"/>


así, que a la propiedad rotation le damos una duración de 2500ms ó 2,5seg e irá la misma de 0 a 360° es decir: una vuelta completa.

Luego añado que mientras el logo va girando, hace un zoom-in de ingreso, es decir que aparezca en pantalla casi desde la nada o 1 puntito a su tamaño final, y esto lo logro con las propiedades de escala, tanto para el eje X como el Y. Ejemplificando para uno de ellos ya que luego tendrán idénticos atributos:

<objectAnimator
                    android:propertyName="scaleX"
                    android:startOffset="7"
                    android:duration="2500"
                    android:valueFrom="0"
                    android:valueTo="0.4"
                    android:valueType="floatType"
                    android:interpolator="@android:interpolator/fast_out_slow_in"/>

la duración nuevamente de 2,5seg ó 2500ms y el valor del zoom desde 0 al 40% de la imagen que creé, esto fue probando ya que, como indica la documantación oficial, todo lo que sea por exceso del tamaño de 192dp será recortado y no quería que mi imagen se truncara

Esto lo podemos leer en:

Dimensiones de la pantalla de presentación
El ícono de la pantalla de presentación usa las mismas especificaciones que los íconos adaptables, de la siguiente manera:
    • Imagen de marca: Debe ser de 200 × 80 dp.
    • Ícono de la app con fondo de ícono: debe ser de 240 × 240 dp y caber dentro de un círculo de 160 dp de diámetro.
    • Ícono de la app sin fondo de ícono: Debe ser de 288 × 288 dp y caber dentro de un círculo de 192 dp de diámetro.
Por ejemplo, si el tamaño completo de una imagen es de 300 × 300 dp, el ícono debe ajustarse a un círculo con un diámetro de 200 dp. Todo lo que está fuera del círculo se vuelve invisible (enmascarado).


9) En MainActivity.kt ahora agrego el código en Kotlin necesario, tanto para presentar la Splash Screen animada como para el botón de finalizar la app.

Thread.sleep(3000) //demora de 3seg
installSplashScreen().setKeepOnScreenCondition{false}

Se incluye una demora de 3000ms ó 3seg para dar tiempo a que se vea la Splash Screen. Esto desde luego no irá en una app final, ya que la idea no es forzar una demora sino presentar la splash mientras en background carga nuestra aplicación.

Con la segunda línea de código mostraré la Splash Screen y luego de la demora indicada pasaré a la actividad principal. Si quisiera que la Splash Screen quede en pantalla y no salga pondría true como condición

Lo que sigue al código para mostrar la pantalla de inicio es el código asignado a mi Button para finalizar la app, el cual está detallado en comentarios en la captura en cada paso del mismo.

10) Como paso final y broche de oro no queda más que probar nuestra app en el emulador, para ello click en play en el emulador seleccionado
