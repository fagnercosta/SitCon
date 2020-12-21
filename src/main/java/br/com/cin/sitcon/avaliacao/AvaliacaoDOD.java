package br.com.cin.sitcon.avaliacao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.Startable;
import org.springframework.web.context.annotation.SessionScope;

import br.com.cin.sitcon.model.Demanda;
import br.com.cin.sitcon.model.ItemNecessidadeEssencial;
import br.com.cin.sitcon.model.ItemObjetivoEstrategico;
import br.com.cin.sitcon.model.TermoRereferencia;
import br.com.cin.sitcon.similarity.CosineSimilarity;

@SessionScope
public class AvaliacaoDOD {
	
	private Demanda demanda;
	private TermoRereferencia termo;
	private boolean objetoConistente;
	private boolean necessidadeConsistente;
	private List<ItemNecessidadeEssencial> necessidades;
	private List<ItemObjetivoEstrategico> objetivoEstrategicos;
	private static double LIMIAR = 0.7;
	
	public AvaliacaoDOD() {
		
	}
	public AvaliacaoDOD(Demanda demanda) {
		this.demanda = demanda;
		System.out.println("Demanda"+this.demanda.getResponsavel());
	}
	
	public AvaliacaoDOD(TermoRereferencia termo) {
		this.termo = termo;
		System.out.println("Demanda"+this.termo.getObjetoContratacao());
	}
	
	
	public AvaliacaoDOD avalicao(Demanda demanda) {
		this.setDemanda(demanda);
		return this;
	}
	
	public AvaliacaoDOD avalicao(TermoRereferencia termo) {
		this.setTermo(termo);
		return this;
	}
	
	public void setObjetoConistente(boolean objetoConistente) {
		this.objetoConistente = objetoConistente;
	}
	
	public boolean isObjetoConistente(Demanda demanda) {
		if(demanda.getObjeto().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}
	
	public void setTermo(TermoRereferencia termo) {
		this.termo = termo;
	}
	
	public TermoRereferencia getTermo() {
		return termo;
	}
	
	public Demanda getDemanda() {
		System.out.println("AQUI");
		return demanda;
	}
	
	public void setNecessidades(List<ItemNecessidadeEssencial> necessidades) {
		this.necessidades = necessidades;
	}
	public List<ItemNecessidadeEssencial> getNecessidades() {
		return necessidades;
	}
	
	public void setObjetivoEstrategicos(List<ItemObjetivoEstrategico> objetivoEstrategicos) {
		this.objetivoEstrategicos = objetivoEstrategicos;
	}
	
	public List<ItemObjetivoEstrategico> getObjetivoEstrategicos() {
		return objetivoEstrategicos;
	}
	
	public List<ItemObjetivoEstrategico> avaliarConsitenciaTexto(List<ItemObjetivoEstrategico> objetivos) {
		List<String> objetivosEs = new ArrayList<String>();
		objetivosEs.add("Propiciar a infraestrutura física e tecnológica adequadas para o desempenho de atividades fins e administrativas");
		objetivosEs.add("Implementar, Aperfeiçoar e Padronizar mecanismos de gestão");
		objetivosEs.add("Fortalecer a comunicação interna e externa (Relacionamento da área de TIC com suários.)");
		objetivosEs.add("Aprimorar os instrumentos normativos e de integridade processual");
		objetivosEs.add("Ampliar o uso de tecnologias educacionais e a oferta da educação a distância");
		objetivosEs.add("Promover a qualidade de vida do servidor");
		
		for(ItemObjetivoEstrategico o : objetivos) {
			CosineSimilarity similarity = new CosineSimilarity();
			for(String text : objetivosEs) {
				double score = similarity.Cosine_Similarity_Score(o.getDescricaoObjetivo(), text);
				System.out.println("SCORE:"+score);
				if(score>=LIMIAR) {
					o.setDescricaoObjetivoConsitente(true);
					break;
				}
			}
		}
		
		return objetivos;		
		
	}
	
	public List<ItemNecessidadeEssencial> avaliarConsitenciaTextoNecessidade(List<ItemNecessidadeEssencial> necessidades) {
		List<String> necessidadeEst = new ArrayList<String>();
		necessidadeEst.add("Prover serviços de suporte, manutenção e garantia aos computadores do IFAM, substituindo-os periodicamente para garantia de desempenho e atualização tecnológica, e atendimento das demandas de crescimento, adequando a configuração dos equipamentos às necessidades gerais e específicas do IFAM.");
		necessidadeEst.add("Mapear, manter, suportar, prover atualização tecnológica e atender demandas por ampliação de toda a infraestrutura de rede de dados (metálica e ótica) do IFAM, incluindo ativos de rede, nobreaks e racks, etc, propiciando conectividade com disponibilidade.");
		necessidadeEst.add("Manter a rede sem fio do IFAM funcional, provendo suporte, renovação, ampliação, melhorias e ajustes nos serviços que garantam a disponibilidade e a conectividade para o acesso, facilidade de uso, segurança e a qualidade do serviço.");
		necessidadeEst.add("Manter, suportar e ampliar o atual parque de servidores e demais recursos de datacenters do IFAM (storages, nobreaks, refrigeração, racks, switches, cabeamento, elétrica, instalações físicas, etc.), incluindo estruturação de novo datacenter para site-backup, com foco em alta disponibilidade e capacidade de atendimento das necessidades, promovendo constante atualização dos recursos e adequada operação.");
		necessidadeEst.add("Incentivar contrato de outsourcing de impressão corporativa, promovendo as devidas adequações no número de equipamentos, assim como adequação às necessidades das unidades do IFAM.");
		necessidadeEst.add("Prover os recursos e as soluções tecnológicas necessárias para implementar e manter a Política de Segurança da Informação (PSI) do IFAM.");
		necessidadeEst.add("Prover suporte na estruturação e reestruturação de laboratórios de informática, assim como em salas de aula, relativamente a recursos de TIC");
		necessidadeEst.add("Realizar transição tecnológica do padrão de transmissão de dados utilizados na infraestrutura física, lógica, aplicações e serviços de rede utilizados atualmente no IFAM, denominado IPv4, para o protocolo IPv6, alinhado com os parâmetros do Plano de Disseminação e Uso do IPv6 no âmbito do SISP.");
		necessidadeEst.add("Prover estrutura organizacional de TIC permanentemente alinhada às políticas do Governo Federal, legislações e aderente ao IFAM, nos níveis estratégico, tático e operacional.");
		necessidadeEst.add("Promover e manter soluções modernas e adequadas às necessidades de comunicação institucional de maneira integrada/unificada (voz, web conferência, videoconferência,etc.), abrangente e de fácil uso pelos usuários, incluindo ferramentas de suporte ao ensino à distância (EAD).");
		necessidadeEst.add("Atualizar, aprovar e implementar as políticas de TIC do IFAM, com vistas à organização e aumento da governança digital e corporativa, tal como política de uso de recursos de TIC (e-mail, computadores, sistemas), etc.");
		necessidadeEst.add("Manter sistemas operacionais servidor, comerciais ou livres, promovendo sua atualização conforme necessidade e ampliando o número de licenças para atendimento das demandas de crescimento e organização da TIC.");
		necessidadeEst.add("Contratar, adquirir, adotar ou desenvolver internamente sistemas, aplicações e sítios institucionais que suportem serviços voltados às áreas meio e fim, aderentes às necessidades do negócio, provendo as devidas manutenções e de acordo com as prioridades do IFAM.");
		necessidadeEst.add("Prover licenciamento de diferentes programas aplicativos ou sistemas de aplicação específica (como bancos de dados), voltados ao atendimento de demandas de ensino e administrativos, com garantia e suporte de manutenção.");
		necessidadeEst.add("Prover adequações nas condições de trabalho das equipes de TIC e ferramental apropriado à execução de suas atividades, por meio de reforma civil, mobiliários, equipamentos de TIC, mais conforto aos profissionais e minimização de riscos a pessoas e serviços, visando aumento de produtividade e qualidade de vida.");
		necessidadeEst.add("Implementar Plano de Capacitação Permanente para profissionais de TIC do IFAM, de acordo com as necessidades institucionais, buscando formar as competências e perfis desejados, incluindo cursos, treinamentos, certificações profissionais, pós-graduação (lato sensu, stricto sensu), participação em fóruns e congressos, etc.");
		
		
		
		for(ItemNecessidadeEssencial n : necessidades) {
			CosineSimilarity similarity = new CosineSimilarity();
			for(String text : necessidadeEst) {
				double score = similarity.Cosine_Similarity_Score(n.getDescricaoDetalhada(), text);
				System.out.println("SCORE:"+score);
				if(score>=LIMIAR) {
					n.setDescricaoNecessidadeConsitente(true);
					break;
				}
			}
		}
		
		return necessidades;		
		
	}
	
	
	

}
