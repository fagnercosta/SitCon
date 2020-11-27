var Opala = Opala || {};

Opala.GraficoPorSituacao = (function() {
	
	function GraficoPorSituacao() {
		this.ctx = $('#graficoS')[0].getContext('2d');		
		this.grafico = null;
	}
	
	GraficoPorSituacao.prototype.iniciar = function() {
		this.grafico = new Chart(this.ctx, {
		    type: 'line',
		    
		    data: {
		    	
		    	labels: ['Em Andamento', 'Concluídos', 'Cancelados','Encerrados','A Iniciar','Em Planejamento','Não Informado','Paralisado'],
		    	datasets: [{
		    		label: 'Situação dos Projetos',
		    		backgroundColor: ["rgba(180, 39, 111, 0.8)","rgba(70, 139, 111, 0.8)","rgba(70, 39, 111, 0.8)","rgba(70, 9, 111, 0.8)","rgba(70, 39, 1, 0.8)","rgba(70, 39, 111, 0.8)","rgba(170, 39, 111, 0.8)","rgba(170, 39, 11, 0.8)"],
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	               
	                data: [100, 5, 10,1]
		    	}]
		    },
		    
		    options: {
		        title: {
		            display: true,
		            text: 'Andamento dos Projetos'
		        }
		    }
		});
		
		
	}
	
	GraficoPorSituacao.prototype.atualizar = function(data) {
		this.grafico.data.datasets[0].data = data;
		this.grafico.update();
		
	}
	
	return GraficoPorSituacao;
	
}());


Opala.GraficoTotal = (function() {
	
	function GraficoTotal() {
		this.ctx = $('#graficoT')[0].getContext('2d');		
		this.graficoT = null;
	}
	
	GraficoTotal.prototype.iniciar = function() {
		this.graficoT = new Chart(this.ctx, {
		    type: 'doughnut',
		    data: {
		    	labels: ['Projetos'],
		    	datasets: [{
		    		label: 'Quantitativo de Projetos',
		    		backgroundColor: ["rgb(191, 0, 255)"],
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                
	                
	               
	                data: [100, 5, 10]
		    	}]
		    },
		    
		    options: {
		        title: {
		            display: true,
		            text: 'Número de  Projetos Cadastrados'
		        }
		    }
		});
		
		
	}
	
	GraficoTotal.prototype.atualizar = function(data) {
		this.graficoT.data.datasets[0].data = data;
		this.graficoT.update();
		
	}
	
	return GraficoTotal;
	
}());

Opala.GraficoProjetosPorInstituto = (function() {
	
	function GraficoProjetosPorInstituto() {
		this.ctx = $('#graficoI')[0].getContext('2d');		
		this.graficoI = null;
	}
	
	GraficoProjetosPorInstituto.prototype.iniciar = function() {
		this.graficoI = new Chart(this.ctx, {
		    type: 'doughnut',
		    data: {
		    	labels: ['IFAC', 'IFAM', 'IFTO','IFRO'],
		    	datasets: [{
		    		label: 'Projetos por Instituição',
		    		backgroundColor: ["rgba(50,205,153)","rgba(215, 227, 44, 0.8)","rgba(215, 39, 44, 0.8)","rgba(70, 39, 255, 0.8)"],
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                
	                
	               
	                data: [100, 5, 10,12]
		    	}]
		    },
		    
		    options: {
		        title: {
		            display: true,
		            text: 'Projetos Cadastrados por Instituição'
		        }
		    }
		});
		
		
	}
	
	GraficoProjetosPorInstituto.prototype.atualizar = function(data) {
		this.graficoI.data.datasets[0].data = data;
		this.graficoI.update();
		
	}
	
	return GraficoProjetosPorInstituto;
	
}());

Opala.GraficoPorte = (function() {
	
	function GraficoPorte() {
		this.ctx = $('#graficoP')[0].getContext('2d');		
		this.graficoP = null;
	}
	
	GraficoPorte.prototype.iniciar = function() {
		this.graficoP = new Chart(this.ctx, {
		    type: 'doughnut',
		    data: {
		    	labels: ['Pequeno', 'Médio', 'Grande'],
		    	datasets: [{
		    		label: 'Projetos por Instituição',
		    		backgroundColor: ["rgba(50,205,153,0.4)","rgb(64, 255, 0,0.3)","rgba(215, 227, 44, 0.3)"],
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                
	                
	               
	                data: [100, 5, 10,12]
		    	}]
		    },
		    
		    options: {
		        title: {
		            display: true,
		            text: 'Porte do Projeto'
		        }
		    }
		});
		
		
	}
	
	GraficoPorte.prototype.atualizar = function(data) {
		this.graficoP.data.datasets[0].data = data;
		this.graficoP.update();
		
	}
	
	return GraficoPorte;
	
	

}());


Opala.GraficoNatureza = (function() {
	
	function GraficoNatureza() {
		this.ctx = $('#graficoN')[0].getContext('2d');		
		this.graficoN = null;
	}
	
	GraficoNatureza.prototype.iniciar = function() {
		this.graficoN = new Chart(this.ctx, {
		    type: 'doughnut',
		    data: {
		    	labels: ['Ensino', 'Pesquisa', 'Extensão','Administração'],
		    	datasets: [{
		    		label: 'Projetos por Natureza',
		    		backgroundColor: ["rgba(70, 39, 111, 0.8)","rgba(52, 255, 78, 0.8)","rgba(255,0,10,0.5)","rgba(0,145,148,0.5)"],
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                
	                
	               
	                data: [100, 5, 10,12]
		    	}]
		    },
		    
		    options: {
		        title: {
		            display: true,
		            text: 'Natureza dos  Projetos'
		        }
		    }
		});
		
		
	}
	
	GraficoNatureza.prototype.atualizar = function(data) {
		this.graficoN.data.datasets[0].data = data;
		this.graficoN.update();
		
	}
	
	return GraficoNatureza;
	
	

}());

Opala.GraficoFerramenta = (function() {
	
	function GraficoFerramenta() {
		this.ctx = $('#graficoF')[0].getContext('2d');		
		this.graficoF = null;
	}
	
	GraficoFerramenta.prototype.iniciar = function() {
		this.graficoF = new Chart(this.ctx, {
		    type: 'horizontalBar',
		    data: {
		    	labels: ['Google Drive', 'RedMine', 'Trello','Suap','Sei','Planilha Eletrônica'],
		    	datasets: [{
		    		label: 'Ferramentas',
		    		backgroundColor: ["rgba(170, 39, 110, 0.8)","rgba(17, 39, 11, 0.8)","rgba(170, 39, 11, 0.8)","rgba(70, 39, 111, 0.8)","rgba(52, 255, 78, 0.8)","rgba(255,0,10,0.5)","rgba(0,145,148,0.5)"],
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "red",
	                
	                
	               
	                data: [100, 5, 10,120]
		    	}]
		    },
		    
		    options: {
		        title: {
		            display: true,
		            text: 'Projetos Gerenciado por Ferramentas'
		        }
		    }
		});
		
		
	}
	
	GraficoFerramenta.prototype.atualizar = function(data) {
		this.graficoF.data.datasets[0].data = data;
		this.graficoF.update();
		
	}
	
	return GraficoFerramenta;
	
	

}());






$(function() {
	
		
		
	
	
	var graficoI = new Opala.GraficoProjetosPorInstituto();
	graficoI.iniciar();
	

	$.ajax({
		  url: '/projeto/totalPorInstituto',
		  type: 'GET',
		  dataType: 'json',
		  success: function(data) {
			  graficoI.atualizar(data);
		  },
		  error: function(err) {
		    console.log(err);
		  }
		});
	
	var graficoP = new Opala.GraficoPorte();
	graficoP.iniciar();
	

	$.ajax({
		  url: '/projeto/dadosPorPorte',
		  type: 'GET',
		  dataType: 'json',
		  success: function(data) {
			  graficoP.atualizar(data);
		  },
		  error: function(err) {
		    console.log(err);
		  }
		});
	
	var graficoN = new Opala.GraficoNatureza();
	graficoN.iniciar();
	

	$.ajax({
		  url: '/projeto/dadosPorNatureza',
		  type: 'GET',
		  dataType: 'json',
		  success: function(data) {
			  graficoN.atualizar(data);
		  },
		  error: function(err) {
		    console.log(err);
		  }
		});
	
	var graficoF = new Opala.GraficoFerramenta();
	graficoF.iniciar();
	

	$.ajax({
		  url: '/projeto/dadosPorFerramenta',
		  type: 'GET',
		  dataType: 'json',
		  success: function(data) {
			  graficoF.atualizar(data);
		  },
		  error: function(err) {
		    console.log(err);
		  }
		});
	
	var graficoS = new Opala.GraficoPorSituacao();
	graficoS.iniciar();
	

	$.ajax({
		  url: '/projeto/dadosPorSituacao',
		  type: 'GET',
		  dataType: 'json',
		  success: function(data) {
			  graficoS.atualizar(data);
		  },
		  error: function(err) {
		    console.log(err);
		  }
		});
	
	
	
	

});