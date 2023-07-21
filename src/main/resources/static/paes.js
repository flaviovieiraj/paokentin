var myModal = new bootstrap.Modal(document.getElementById("modalAltera"));

fetch("http://localhost:8080/Pao", {
    method: "GET",
    headers: { "Content-type": "application/json;charset=UTF-8" },
})
    .then((data) => data.json())
    .then((json) => {
        const divMain = document.getElementById("main");
        
        let s =
            `<table class='table table-striped table-hover table-warning table-striped-columns'>` + 
            `<tr><th>#</th><th>Tipo de Pão</th><th>Tempo de Preparo</th><th>Ações</th></tr>`;

        json.forEach((element) => {
            s +=
                `<tr><td>${element.codigo}</td><td>${element.tipoPao}</td><td>${element.tempoPreparo} minutos</td>` +
                `<td><button class="btn btn-dark" onclick="abrirModalAltera(${element.codigo})" >alterar</button>` +
                `<button class="btn btn-danger"  onclick="deletar(${element.codigo})">deletar</button></td></tr>`;
        });

        /*for(let i = 0; i < json.length; i++){
                s += '<tr><td>'+json[i].codigo+'</td><td>'+json[i].nome+'</td><td>'+json[i].contato+'</td></tr>'
            }*/

        s += "</table>";

        divMain.innerHTML = s;
    })
    .catch((err) => {
        document.getElementById("main").innerHTML = "Erro ao recuperar a lista";
    });

async function abrirModalAltera(codigo) {
    myModal.show();

    const retorno = await fetch("http://localhost:8080/Pao/" + codigo, {
        method: "GET",
        headers: { "Content-type": "application/json;charset=UTF-8" },
    });

    retorno.json().then((json) => {
        document.getElementById("txtCodigo").value = json.codigo;
        document.getElementById("txtTipoPao").value = json.tipoPao;
        document.getElementById("txtTempoPreparo").value = json.tempoPreparo;
    });
}

async function deletar(codigo) {
    if (confirm("Deseja realmente deletar o registro?")) {
        const retorno = await fetch("http://localhost:8080/Pao/" + codigo, {
            method: "DELETE",
            headers: { "Content-type": "application/json;charset=UTF-8" },
        });

        location.reload();
    }
}
