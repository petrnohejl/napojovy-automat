/**
 * Automat na napoje
 * Hlavni trida programu
 * 
 * @author Petr Nohejl
 * @version 27.12.2009
 */
 
class Main
{
	public static void main (String arg[])
	{
		// vytvoreni automatu
		Automat a = new Automat();
		
		// pridani napoju do automatu (id, price, count, name)
		a.AddDrink(1, 25, 10, "Coca cola");
		a.AddDrink(2, 22, 8, "Fanta");
		a.AddDrink(3, 20, 11, "Sprite");
		a.AddDrink(4, 56, 2, "Red Bull");
		a.AddDrink(5, 34, 5, "Bonaqua");
		a.AddDrink(6, 12, 0, "Ice tea");
		a.AddDrink(7, 17, 20, "Rajec");
		
		// pridani minci do automatu (id, value, count)
		a.AddCoin(1, 1, 4);
		a.AddCoin(2, 2, 2);
		a.AddCoin(3, 5, 4);
		a.AddCoin(4, 10, 10);
		a.AddCoin(5, 20, 0);
		a.AddCoin(6, 50, 0);
		
		// kontrolni vypis
		//a.PrintDrinks();
		//a.PrintCoins();
		
		// spusteni automatu
		a.StartAutomat();
	}
}
