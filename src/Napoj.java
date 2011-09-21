/**
 * Automat na napoje
 * Trida reprezentujici napoj
 * 
 * @author Petr Nohejl
 * @version 27.12.2009
 */

class Napoj
{
	// promenne tridy
	private int id;				// identifikator
	private int price;			// cena
	private int count;			// pocet napoju
	private String name;		// nazev napoje
	
	// nastaveni promennych tridy
	public void SetId(int napoj_id) { id = napoj_id; }
	public void SetPrice(int napoj_price) { price = napoj_price; }
	public void SetCount(int napoj_count) { count = napoj_count; }
	public void SetName(String napoj_name) { name = napoj_name; }
	
	// ziskani promennych tridy
	public int GetId() { return id; }
	public int GetPrice() { return price; }
	public int GetCount() { return count; }
	public String GetName() { return name; }
	
	/**
     * Inkrementace poctu napoju
     */
	public void IncCount() { count++; }
	
	/**
     * Dekrementace poctu napoju
     */
	public void DecCount() { count--; }
	
	/**
     * Konstruktor tridy
     */
	Napoj(int napoj_id, int napoj_price, int napoj_count)
	{
		this.id = napoj_id;
		this.price = napoj_price;
		this.count = napoj_count;
		this.name = "Napoj " + napoj_id;
	}
	
	/**
     * Konstruktor tridy
     */
	Napoj(int napoj_id, int napoj_price, int napoj_count, String napoj_name)
	{
		this.id = napoj_id;
		this.price = napoj_price;
		this.count = napoj_count;
		this.name = napoj_name;
	}
	
	/**
     * Je dostatek napoju?
     */
	public boolean IsEnoughDrinks(int number)
	{
		return (number <= this.count);
	}
	
	/**
     * Vytiskne udaje o napoji
     */
	public void PrintNapoj()
	{
		System.out.println("NAPOJ:");
		System.out.println("Id: " + id);
		System.out.println("Nazev: " + name);
		System.out.println("Cena: " + price);
		System.out.println("Pocet: " + count + "\n");
	}
}
