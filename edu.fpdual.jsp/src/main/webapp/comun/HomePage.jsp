<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Juego de adivinanza en JSP</title>
  </head>
  <body>
    <h1>Juego de adivinanza en JSP</h1>
    <p id="mensaje"></p>
    <p id="letras"></p>
    <p id="intentos"></p>
    <label>Introduce una letra:</label>
    <input type="text" id="letra" maxlength="1">
    <button id="boton">Enviar</button>
    <button id="reiniciar">Reiniciar</button>
    <script>
      var palabras = ["codigo", "binario", "string", "comando","bug", "depurar",
        "compilar","bucle","array","variable","algoritmo","interfaz","clase","objeto","refactorizacion"];
      var palabra = palabras[Math.floor(Math.random() * palabras.length)];
      var letrasAdivinadas = Array(palabra.length).fill("-");
      var intentos = 10;

      var mensaje = document.getElementById("mensaje");
      var letras = document.getElementById("letras");
      var intentosEl = document.getElementById("intentos");
      var letraInput = document.getElementById("letra");
      var boton = document.getElementById("boton");
      var reiniciar = document.getElementById("reiniciar");

      function mostrarMensaje(texto) {
        mensaje.textContent = texto;
      }

      function mostrarLetras(letras) {
        letras.textContent = letrasAdivinadas.join(" ");
      }

      function mostrarIntentos(numIntentos) {
        intentosEl.textContent = "Te quedan " + numIntentos + " intentos.";
      }

      function actualizarJuego() {
        mostrarLetras(letras);
        mostrarIntentos(intentos);

        if (letrasAdivinadas.join("") === palabra) {
          mostrarMensaje("¡ENHORABUENA, Ganaste! La palabra era " + palabra);
          reiniciar.style.display = "inline";
          boton.disabled = true;
        } else if (intentos === 0) {
          mostrarMensaje("Perdiste :( La palabra era " + palabra);
          reiniciar.style.display = "inline";
          boton.disabled = true;
        }
      }

      actualizarJuego();

      boton.addEventListener("click", function() {
        var letra = letraInput.value.toLowerCase();

        if (letra.match(/^[a-z]$/)) {
          letraInput.value = "";
          var acierto = false;

          for (var i = 0; i < palabra.length; i++) {
            if (palabra.charAt(i) === letra) {
              letrasAdivinadas[i] = letra;
              acierto = true;
            }
          }

          if (!acierto) {
            intentos--;
          }

          actualizarJuego();
        }
      });

      reiniciar.addEventListener("click", function() {
        window.location.reload();
      });
    </script>
  </body>
</html>
