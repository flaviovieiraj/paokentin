function atualizarFornadaNoServidor(fornada) {
    fetch(`http://localhost:8080/Fornada/${fornada.codigo}`, {
        method: "PUT",
        body: JSON.stringify(fornada),
        headers: { "Content-type": "application/json; charset=UTF-8" },
    })
    .then(response => response.json())
    .then(data => console.log("Fornada atualizada com sucesso"))
    .catch(error => console.error("Erro ao atualizar fornada:", error));
}

function atualizarStatusFornadas(json) {
	
    const divMain = document.getElementById("main");
    
    let s = "<table class='table table-striped table-hover table-warning table-striped-columns'>" + "<tr><th>Tipo de Pão</th><th>Última Fornada</th><th>&#127777;</th><th>Preparo</th></tr>";
    
    const ultimaFornadaPorTipo = {};
    
    json.forEach(element => {
		
        const paoCodigo = element.pao.codigo;
        
	// Verifica se já existe uma fornada daquele pão, e se existir insere apenas a mais recente
	
	if (!ultimaFornadaPorTipo[paoCodigo]) {
            ultimaFornadaPorTipo[paoCodigo] = element;
        } else {
             if (element.horaInicio > ultimaFornadaPorTipo[paoCodigo].horaInicio) {
                ultimaFornadaPorTipo[paoCodigo] = element;
            }
        }
        })
        
        const fornadasOrdenadas = Object.values(ultimaFornadaPorTipo).map(element =>{
			const horaInicioMillis = new Date(element.horaInicio).getTime();
            const tempoPreparoMillis = element.pao.tempoPreparo * 60 * 1000;
            const horaTerminoMillis = horaInicioMillis + tempoPreparoMillis;
            element.horaTermino = horaTerminoMillis;
            return element;
		}).sort((a, b) => b.horaTermino - a.horaTermino); // Ordenando pela hora de termino mais recente
        
        fornadasOrdenadas.forEach(element => {
			
		const tipoPao = element.pao.tipoPao;        
        const horaInicio = new Date(element.horaInicio);
        
        const tempoPreparoMillis = element.pao.tempoPreparo * 60 * 1000;
        const horaTerminoMillis = horaInicio.getTime() + tempoPreparoMillis;
        const horaTermino = new Date(horaTerminoMillis).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
        
		//const dataTermino = new Date(horaTerminoMillis).toLocaleDateString([], { day: 'numeric', month: 'numeric' });
        
        // Verifica se a hora atual é igual ou superior a hora de termino da fornada para definir "quente = true"
        // e se já passaram 15 minutos, define como "quente=false" novamente        		
        
        const horaAtualMillis = new Date().getTime();
        
        if (horaAtualMillis >= horaTerminoMillis && horaAtualMillis < horaTerminoMillis + 900000) {
			if(!element.quente){
            element.quente = true; 
				element.pronto = true;          
             atualizarFornadaNoServidor(element);
             }
            
        } else  {
			if(element.quente){
            element.quente = false;            
             atualizarFornadaNoServidor(element);
             }           
        }
        
        
        
        
         const emojiQuente = element.quente ? "&#128293;" : "&#129482;";
         const emojiPronto = element.pronto ? "&#127838;" : "&#x23F2;";
         
         if (!element.pronto) {
			 const tempoRestanteMillis = element.horaTermino - new Date().getTime();
            const minutosRestantes = Math.ceil(tempoRestanteMillis / 60000);
            const contadorRegressivo = minutosRestantes > 0 ? `${minutosRestantes} min` : `< 1 min`;
			 
			  s += `<tr><td>${tipoPao}</td><td>${horaTermino}</td><td>${emojiQuente}</td><td>${emojiPronto} ${contadorRegressivo}</td>`;
        } else {
            s += `<tr><td>${tipoPao}</td><td>${horaTermino}</td><td>${emojiQuente}</td><td>${emojiPronto} Pronto</td>`;
        }
         
    });
    s += "</table>";
    divMain.innerHTML = s;
}

function carregarFornadas() {
    fetch("http://localhost:8080/Fornada",{
        method: "GET",
        headers: {"Content-type": "application/json;charset=UTF-8"}
    }).then(data => data.json())
    .then(json => {
        atualizarStatusFornadas(json);
    }).catch(err => {
        document.getElementById("main").innerHTML = "Erro ao recuperar a lista";
    });
}

// Chama a função de carregar as fornadas e iniciar a verificação a cada 30seg
carregarFornadas();
setInterval(carregarFornadas, 30000);
