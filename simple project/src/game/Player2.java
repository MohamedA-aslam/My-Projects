package game;

public class Player2 extends Player1{
	
	private int health;
	private boolean armour;
	public Player2(String name, String gun, int health,  boolean armour) {
		super(name, gun, health);
		if(health<=0||health>100) this.health=100;
		else this.health = health;
		this.armour = armour;
	}
	
	

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return this.health;
	}



	@Override
	public void damageByGun1() {
		if(armour) {
			this.health-=20;
			if(this.health<=0) 	this.health=0;
			
			System.out.println("Armour is on.Got hit by GUN '1', health reduced by 20."+"Current health is "+this.health);
			
		}
		if(!armour) {
			this.health-=30;
			if(this.health<=0) 	this.health=0;
			
			System.out.println("Armour is off.Got hit by GUN '1', health reduced by 30."+"Current health is "+this.health);
			
		}
		if(this.health==0)
			System.out.println(getName()+" is dead");
		}



	@Override
	public void damageByGun2() {
		if(armour) {
			this.health-=40;
			if(this.health<=0) 	this.health=0;
			
			System.out.println("Armour is on.Got hit by GUN '2', health reduced by 40."+"Current health is "+this.health);
			
		}
		if(!armour) {
			this.health-=50;
			if(this.health<=0) 	this.health=0;
			
			System.out.println("Armour is off.Got hit by GUN '2', health reduced by 50."+"Current health is "+this.health);
			
		}
		if(this.health==0)
			System.out.println(getName()+" is dead");
		
	}



	@Override
	public void heal() {
		// TODO Auto-generated method stub
		
		if(armour) {
			if(this.health==0) System.out.println(getName()+" is dead, Healing is not possible.");
			else {
				this.health +=60;
				if(this.health>100) this.health=100;
			}
			
		}
		if(!armour) {
			if(this.health==0) System.out.println(getName()+" is dead, Healing is not possible.");
			else {
				this.health +=50;
				if(this.health>100) this.health=100;
			}
			
		}
		System.out.println("Current health increased to "+this.health);

	}
	
	
	
		
	
	
	
	

}
