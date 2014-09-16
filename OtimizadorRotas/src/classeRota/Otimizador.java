package classeRota;//inserção no pacote classeRota para pleno acesso de seu conteúdo

import java.util.Collection;
import java.util.Iterator;

import lejos.geom.Line;
import lejos.geom.Rectangle;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.ShortestPathFinder;
import lejos.util.Delay;

public class Otimizador//nome da Classe Executável
{
	public static void main(String[] args)
	throws Exception//Método que será executado pelo NXT
	{
		Rotas r = new Rotas();//Cria objeto da classe Rotas e automaticamente seu método construtor
		/*r.inicializaMenuTela();//Chama o método inicializaMenuTela
		r.esperaDecisaoMenu();//Chama o método esperaDecisaoMenu
		*/
		Rectangle rec = new Rectangle(0,0,100,100);
		Line[] l = new Line[8];
		/*Line l1 = new Line(10,20,20,20);
		Line l2 = new Line(10,10,10,20);
		Line l3 = new Line(20,20,10,10);
		Line l4 = new Line(20,10,10,10);
		Line l5 = new Line(40,20,50,20);
		Line l6 = new Line(40,10,40,20);
		Line l7 = new Line(50,20,40,10);
		Line l8 = new Line(50,10,40,10);*/
		/*Line l1 = new Line(10,10,40,10);
		Line l2 = new Line(20,20,40,20);
		Line l3 = new Line(30,30,40,30);
		Line l4 = new Line(40,40,40,40);
		Line l5 = new Line(10,10,10,40);
		Line l6 = new Line(20,20,20,40);
		Line l7 = new Line(30,30,30,40);
		Line l8 = new Line(40,40,40,40);*/
		Line l1 = new Line(30,10,30,39);//sobe
		Line l2 = new Line(40,10,40,39);//sobe paral
		Line l3 = new Line(30,40,40,59);//diag dir
		Line l4 = new Line(40,40,30,59);//diag esq
		Line l5 = new Line(41,40,49,40);//
		Line l6 = new Line(20,20,20,40);
		Line l7 = new Line(30,30,30,40);
		Line l8 = new Line(40,40,40,40);
		// COMENTÁRIO INÚTIL
		l[0] = l1;
		l[1] = l2;
		l[2] = l3;
		l[3] = l4;
		l[4] = l5;
		l[5] = l6;
		l[6] = l7;
		l[7] = l8;
		r.setMapa(l,rec);
		ShortestPathFinder s = new ShortestPathFinder(r.mapa);
		Pose origem = new Pose(0,0,0);
		Waypoint destino = new Waypoint(20,30,0);
		Path p = s.findRoute(origem, destino);
		DifferentialPilot pilot = new DifferentialPilot(1,4,Motor.B,Motor.C,true);
		Navigator navigator = new Navigator(pilot);
		navigator.followPath(p);
		System.out.println("Waypoints:");
		//int i = 0;
		LCD.clear();
		for(Iterator<Waypoint> it = p.iterator(); it.hasNext();)
		{
			System.out.println(it.next().toString());
			//System.out.println(i);
			//i++;
		}
		//navigator.followPath();
		Button.ENTER.waitForPressAndRelease();
		Delay.msDelay(1000);
	}

}
