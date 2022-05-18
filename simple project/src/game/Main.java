package game;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Player1 p1 = new Player1("Aslam","sword",00);
		
		
//		p1.damageByGun1();
//		p1.damageByGun2();
		p1.damageByGun1();
		p1.heal();
		p1.damageByGun2();
		
		Player2 p2= new Player2("Maverick", "Ryu", 0, true);
		p2.damageByGun2();
		p2.damageByGun2();
		p2.heal();
		p2.damageByGun2();
		
		
		System.out.println(p2.getName());
		System.out.println(p2.getHealth());
		System.out.println(p2.getGun());
	}

}
