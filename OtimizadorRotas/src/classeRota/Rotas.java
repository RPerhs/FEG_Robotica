package classeRota;//Inserção no pacote classeRota para acesso pleno ao seu conteúdo

//Importação das bibliotecas necessárias para o projeto
import lejos.geom.Line;
import lejos.geom.Point;
import lejos.geom.Rectangle;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.util.Delay;

public class Rotas//nome da classe (novo tipo de "variável")
{
	//Escolha dos atributos da classe, seus tipos, privacidade de acesso (visibilidade) e nome de referência no projeto
	public  LineMap mapa;
	public  Point casa, escritorio, supermercado; 
	public  String sCasa, sEscritorio, sSupermercado, sGuia;
	public  int telaExibidaMenu;
	public  Pose pontoAtual;
	public  Waypoint pontoFinal;
	public  static LCD tela;
	
	public Rotas() //Método Construtor
	{ 
	    //Mensagem para escolha do destino casa
		setsCasa("Decida o destino"+	//1º Linha
				 "                "+	//2º Linha
				 "                "+	//3º Linha
				 "      CASA      "+	//4º Linha
				 "				  "+	//5º Linha
				 "                "+	//6º Linha
				 "                ");	//7º Linha
		//		  0123456789012345
		//Mensagem para escolha do destino escritório
		setsEscritorio("Decida o destino"+	//1º Linha
				       "                "+	//2º Linha
				       "                "+	//3º Linha
				       "   ESCRITORIO   "+	//4º Linha
				       "                "+	//5º Linha
				       "                "+	//6º Linha
				       "                ");	//7º Linha
		//		        0123456789012345
		//Mensagem para escolha do destino supermercado
		setsSupermercado("Decida o destino"+	//1º Linha
				         "                "+	//2º Linha
				         "                "+	//3º Linha
				         "  SUPERMERCADO  "+	//4º Linha
				         "				  "+	//5º Linha
				         "                "+	//6º Linha
				         "                ");	//7º Linha
		//		          0123456789012345
		//Mensagem de instrução para o usuário  
		setsGuia("*Seja bem vindo*"+	//1º Linha
				 "Mude seu destino"+	//2º Linha
				 "com as teclas de"+	//3º Linha
				 "dir >  ou  esq <"+	//4º Linha
				 "e para confirmar"+	//5º Linha
				 "utilizar o ENTER"+	//6º Linha
				 " Divirta-se :) ");	//7º Linha
		//		  0123456789012345*/
	}
	
	public void inicializaMenuTela()//inicializa o LCD no NXT
	{
		tela = new LCD();
		LCD.clear(); //limpa a tela
		System.out.println(getsGuia().toString());//LCD.drawString(getsGuia(),0,0);//Carrega a mensagem guia no LCD
		Delay.msDelay(500);//Garante a exibição da mensagem 
		setTelaExibidaMenu(0);//seta que a tela 0 esta sendo exibida
	}
	
	public void esperaDecisaoMenu()//responsável pelo tratamento da interface com o usuário durante o menu
	{
		int a = 0;//variável auxiliar para o laço sem fim temporal determinado
		String s = "";//variável auxiliar para descobrir o destino escolhido
		while (a == 0)//laço iniciado para aguardar a escolha do usuário
		{
			if (Button.ENTER.isDown())//caso o botão ENTER seja pressionado
			{
				switch(getTelaExibidaMenu())//analisa qual a tela está sendo exibida
				{
					case 0:break;//nada faz se o ENTER for pressionado durante o menu Guia
					case 1:s = "      CASA      ";a = 1;break;//CASA foi escolhida como destino
					case 2:s = "   ESCRITORIO   ";a = 2;break;//ESCRITORIO foi escolhido como destino
					case 3:s = "  SUPERMERCADO  ";a = 3;break;//SUPERMERCADO foi escolhido como destino
					//			0123456789012345
				}
			}
		    if (Button.ESCAPE.isDown())//caso o botão ESCAPE seja pressionado
		    {
		    	switch(getTelaExibidaMenu())
				{
					case 0:Delay.msDelay(500);break;
					case 1:Delay.msDelay(500);break;
					case 2:Delay.msDelay(500);break;
					case 3:Delay.msDelay(500);break;
				}
		    }
		    if (Button.LEFT.isDown())//Caso o botão LEFT seja pressionao
		    {
		    	switch(getTelaExibidaMenu())//analisa qual a tela está sendo exibida
				{
					case 0:LCD.clear();//Limpa a tela
						   System.out.println(getsSupermercado());//Carrega a mensagem do supermercado
						   setTelaExibidaMenu(3); //altera o dado correspondente a qual tela está sendo exibida
						   Delay.msDelay(500);break;//Minimiza o erro por trepidação do botão
					case 1:LCD.clear();System.out.println(getsGuia());setTelaExibidaMenu(0);Delay.msDelay(500);break;
					case 2:LCD.clear();System.out.println(getsCasa());setTelaExibidaMenu(1);Delay.msDelay(500);break;
					case 3:LCD.clear();System.out.println(getsEscritorio());setTelaExibidaMenu(2);Delay.msDelay(500);break;
				}
		    }
		    if (Button.RIGHT.isDown())//Caso o botão RIGHT seja pressionado
		    {
		    	switch(getTelaExibidaMenu())
				{
					case 0:LCD.clear();System.out.println(getsCasa());setTelaExibidaMenu(1);Delay.msDelay(500);break;
					case 1:LCD.clear();System.out.println(getsEscritorio());setTelaExibidaMenu(2);Delay.msDelay(500);break;
					case 2:LCD.clear();System.out.println(getsSupermercado());setTelaExibidaMenu(3);Delay.msDelay(500);break;
					case 3:LCD.clear();System.out.println(getsGuia());setTelaExibidaMenu(0);Delay.msDelay(500);break;
				}
		    }
		}
		exibeDestinoEscolhido(s);//Chama o método responsável por exibir o destino escolhido
	}
	
	public void exibeDestinoEscolhido(String s)//Exibe o destino escolhido por 3 segundos
	{
		//Carrega mensagem com o destino alterável, repassado pelo parâmetro s
		String msg = ("Voce escolheu o "+	//1º Linha
				      "destino seguinte"+	//2º Linha
				      "                "+	//3º Linha
				         s.toString()   +	//4º Linha
				      "                "+	//5º Linha
				      "   Boa viagem   "+	//6º Linha
				      "espere 3 seg ...");	//7º Linha
		//		       0123456789012345
		
		LCD.clear();
		System.out.println(msg.toString());
		
		//Abaixo dois métodos equivalentes para que o sistema aguarde um tempo determinado
		Delay.msDelay(3000);//Faz com que o sistema aguarde por 3000 milisegundos, ou seja, 3 segundos
		Sound.pause(3000);//Faz com que o sistema aguarde por 3000 milisegundos, ou seja, 3 segundos
	}
	
	//Métodos acessores set e get para telaExibidaMenu 
	public void setTelaExibidaMenu(int i)
	{
		this.telaExibidaMenu = i;
	}
	public int getTelaExibidaMenu()
	{
		return this.telaExibidaMenu;
	}
	
	//Métodos acessores set e get para sCasa
	public void setsCasa(String s)
	{
		this.sCasa = s;
	}
	public String getsCasa()
	{
		return this.sCasa;
	}
	
	//Métodos acessores set e get para sEscritorio
	public void setsEscritorio(String s)
	{
		this.sEscritorio = s;
	}
	public String getsEscritorio()
	{
		return this.sEscritorio;
	}
	
	//Métodos acessores set e get para sSupermercado
	public void setsSupermercado(String s)
	{
		this.sSupermercado = s;
	}
	public String getsSupermercado()
	{
		return this.sSupermercado;
	}
	
	//Métodos acessores set e get para sGuia
	public void setsGuia(String s)
	{
		this.sGuia = s;
	}
	public String getsGuia()
	{
		return sGuia;
	}
	
	public Point getCasa()
	{
		return this.casa;
	}
	public void setCasa(Point p)
	{
		this.casa = p;
	}
	public Point getSupermercado()
	{
		return this.supermercado;
	}
	public void setSupermercado(Point p)
	{
		this.supermercado = p;
	}
	public Point getEscritorio()
	{
		return this.escritorio;
	}
	public void setEscritorio(Point p)
	{
		this.escritorio = p;
	}
	public Waypoint getPontoFinal()
	{
		return this.pontoFinal;
	}
	public void setPontoFinal(Waypoint p)
	{
		this.pontoFinal = p;
	}
	public Pose getPontoAtual()
	{
		return this.pontoAtual;
	}
	public void setPontoAtual(Pose p)
	{
		this.pontoAtual = p;
	}
	public void setMapa(Line[] l, Rectangle r)
	{
		mapa = new LineMap(l, r);
	}
}
