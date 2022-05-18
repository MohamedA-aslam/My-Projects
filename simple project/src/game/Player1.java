package game;

public class Player1 {
	private String name;
	private String gun;
	private int health;
	
	public Player1(String name, String gun, int health) {
		this.name = name;
		this.gun = gun;
		if(health <=0||health > 100)this.health=100;
		else this.health = health;
		
	}
	
	public void damageByGun1() {
		this.health -=30;
		if(this.health<=0) 	this.health=0;
			
		
	System.out.println("Got hit by GUN '1', health reduced by 30."+"Current health is "+this.health);
		if(this.health==0)
			System.out.println(getName()+" is dead");// or getName()+" is dead"
	}
	public void damageByGun2() {
		this.health -=50;
		if(this.health<=0) {
			this.health=0;
		}
	System.out.println("Got hit by GUN '2' , health reduced by 30."+"Current health is "+this.health);
	if(this.health==0)
		System.out.println(getName()+" is dead");// or getName()+" is dead"
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGun() {
		return this.gun;
	}

	public void setGun(String gun) {
		this.gun = gun;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void heal() {
		if(this.health==0) System.out.println(getName()+" is dead, Healing is not possible.");
		else {
			this.health +=50;
			if(this.health>100) this.health=100;
		}
			
		System.out.println("Current health increased to "+this.health);

	}
	

}
