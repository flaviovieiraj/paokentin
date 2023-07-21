document.addEventListener("DOMContentLoaded", function () {
	
  fetch("http://localhost:8080/Pao", {
    method: "GET",
    headers: { "Content-type": "application/json;charset=UTF-8" },
  })
    .then((response) => response.json())
    .then((json) => {
      const divMain = document.getElementById("main");

      let s = "<table class='table table-striped table-hover table-warning table-striped-columns'>";
      s +=
        "<tr><th>#</th><th>Tipo de Pão</th><th>Tempo de Preparo</th><th>Ações</th></tr>";

      json.forEach((element) => {
        s +=
          `<tr><td>${element.codigo}</td><td>${element.tipoPao}</td><td>${element.tempoPreparo} minutos</td>` +
          `<td><button class="btn btn-dark" onclick="iniciarFornada(${JSON.stringify(element.codigo)})">Iniciar Fornada</button></tr>`;
      });

      s += "</table>";

      divMain.innerHTML = s;
    })
    .catch((err) => {
      document.getElementById("main").innerHTML = "Erro ao recuperar a lista";
    });
});

function iniciarFornada(pao) {
  const fornada = {
    codigo: 10, 
    horaInicio: new Date(),
    pronto: false,
    quente: false,
    pao: {
		codigo: pao
	}
  };

  
  fetch("http://localhost:8080/Fornada", {
    method: "POST",
    body: JSON.stringify(fornada),
    headers: { "Content-type": "application/json; charset=UTF-8" },
  })
    .then((data) => {
      document.getElementById("result").innerHTML = "Fornada iniciada!";
    })
    .catch((erro) => {
      document.getElementById("result").innerHTML = "Erro!";
    });

  window.alert("Fornada iniciada!");
}
