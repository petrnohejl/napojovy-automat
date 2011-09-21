/**
 * Automat na napoje
 * Trida reprezentujici minci
 * 
 * @author Petr Nohejl
 * @version 27.12.2009
 */

class Mince
{
	// promenne tridy
	private int id;				// identifikator
	private int value;			// nominalni hodnota mince
	private int count;			// pocet minci
	
	// nastaveni promennych tridy
	public void SetId(int mince_id) { id = mince_id; }
	public void SetValue(int mince_value) { value = mince_value; }
	public void SetCount(int mince_count) { count = mince_count; }
	
	// ziskani promennych tridy
	public int GetId() { return id; }
	public int GetValue() { return value; }
	public int GetCount() { return count; }	
	
	/**
     * Inkrementace poctu minci
     */
	public void IncCount() { count++; }
	
	/**
     * Dekrementace poctu minci
     */
	public void DecCount() { count--; }
	
	/**
     * Konstruktor tridy
     */
	Mince(int mince_id, int mince_value, int mince_count)
	{
		this.id = mince_id;
		this.value = mince_value;
		this.count = mince_count;
	}
	
	/**
     * Vytiskne udaje o minci
     */
	public void PrintMince()
	{
		System.out.println("MINCE:");
		System.out.println("Id: " + id);
		System.out.println("Hodnota: " + value);
		System.out.println("Pocet: " + count + "\n");
	}
}
