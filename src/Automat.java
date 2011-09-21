/**
 * Automat na napoje
 * Trida reprezentujici napojovy automat
 * 
 * @author Petr Nohejl
 * @version 27.12.2009
 */

import java.io.*;
import java.util.Vector;

class Automat
{
	// promenne tridy
	private Vector<Napoj> n;						// pole napoju
	private Vector<Mince> m;						// pole minci
	private boolean run = true;						// beh automatu
	private static final String CURRENCY = "Kc";	// mena
	private static final String PIECE = "ks";		// kus
	private static final String PROMPT = ">>> ";	// prompt
	private static final String SEPARATOR = "--------------------------------------------------";	// separator

	/**
     * Konstruktor tridy
     */
	Automat()
	{		
		this.n = new Vector<Napoj>();	// deklarace vektoru napoju
		this.m = new Vector<Mince>();	// deklarace vektoru minci
	}
	
	/**
     * Prida napoj do automatu
     * 
     * @param  id      unikatni ID cislo napoje, vetsi nez 0
     * @param  price   cena napoje, vetsi nez 0
     * @param  count   pocet napoju	      
     * @return         vrati true pokud byl napoj uspesne pridan 
     */
	public boolean AddDrink(int id, int price, int count)
	{
		if(IsDrinkIdUnicate(id) && id>0 && price>0 && count>=0)
		{
			Napoj drink = new Napoj(id, price, count);
			this.n.add(drink);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
     * Prida napoj do automatu
     * 
     * @param  id      unikatni ID cislo napoje, vetsi nez 0
     * @param  price   cena napoje, vetsi nez 0
     * @param  count   pocet napoju	   
     * @param  name    nazev napoje	  	    
     * @return         vrati true pokud byl napoj uspesne pridan 
     */
	public boolean AddDrink(int id, int price, int count, String name)
	{
		if(IsDrinkIdUnicate(id) && id>0 && price>0 && count>=0 && name!="")
		{
			Napoj drink = new Napoj(id, price, count, name);
			this.n.add(drink);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
     * Prida minci do automatu
     * 
     * @param  id      unikatni ID cislo mince, vetsi nez 0
     * @param  value   nominalni hodnota mince, vetsi nez 0
     * @param  count   pocet minci	   	  	    
     * @return         vrati true pokud byla mince uspesne pridana 
     */
	public boolean AddCoin(int id, int value, int count)
	{
		if(IsCoinIdUnicate(id) && id>0 && value>0 && count>=0)
		{
			Mince coin = new Mince(id, value, count);
			this.m.add(coin);
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	/**
     * Vypise napoje v automatu
     */
	public void PrintDrinks()
	{
		System.out.println("SEZNAM NAPOJU [id, nazev, cena, pocet]:");
		for(int i=0; i < this.n.size(); i++)
		{
			Napoj drink = (Napoj) n.elementAt(i);						// reference na objekt napoje
			System.out.print(drink.GetId() + ") ");						// vypis id
			System.out.print(drink.GetName() + " - ");					// vypis nazvu
			System.out.print(drink.GetPrice() + CURRENCY + " - ");		// vypis ceny
			System.out.println(drink.GetCount() + PIECE);				// vypis poctu
		}
		System.out.println("0) Vypnout automat");						// exit
	}
	
	/**
     * Vypise mince v automatu
     */
	public void PrintCoins()
	{
		System.out.println("MINCE [id, hodnota, pocet]:");
		for(int i=0; i < this.m.size(); i++)
		{
			Mince coin = (Mince) m.elementAt(i);						// reference na objekt mince
			System.out.print(coin.GetId() + ") ");						// vypis id
			System.out.print(coin.GetValue() + CURRENCY + " - ");		// vypis hodnoty
			System.out.println(coin.GetCount() + PIECE);				// vypis poctu
		}
		System.out.println("0) Storno");								// exit
	}
	
	/**
     * Ziska referenci na napoj podle zadaneho id
     * 
     * @param  id      ID napoje 	  	    
     * @return         vrati ukazatel na napoj, v pripade neuspechu vrati null
     */
	public Napoj GetDrinkById(int id)
	{
		for(int i=0; i < this.n.size(); i++)
		{
			Napoj drink = (Napoj) n.elementAt(i);						// reference na objekt napoje
			if(drink.GetId() == id) return drink;						// vrati napoj s danym id
		}
		return null;
	}
	
	/**
     * Ziska referenci na minci podle zadaneho id
     * 
     * @param  id      ID mince 	  	    
     * @return         vrati ukazatel na minci, v pripade neuspechu vrati null
     */
	public Mince GetCoinById(int id)
	{
		for(int i=0; i < this.m.size(); i++)
		{
			Mince coin = (Mince) m.elementAt(i);						// reference na objekt mince
			if(coin.GetId() == id) return coin;							// vrati minci s danym id
		}
		return null;
	}
	
	// najde co nejvyssi minci, ktera je k dispozici na vraceni, pokud mince neni k dispozici, vrati null
	private Mince GetOvervalueCoin(int overvalue)
	{
		int finalvalue = 0;
		Mince finalcoin = null;
		
		for(int i=0; i < this.m.size(); i++)
		{
			Mince coin = (Mince) m.elementAt(i);						// reference na objekt mince
			// nalezeni vhodne mince
			if(coin.GetValue() <= overvalue && coin.GetValue() > finalvalue && coin.GetCount() > 0)
			{
				finalvalue = coin.GetValue();
				finalcoin = coin;
			}
		}
		return finalcoin;
	}
	
	// vraceni penez
	private void ReturnCoins(int value)
	{
		// vraceni jednotlivych minci
		while(value>0)
		{
			Mince coin = GetOvervalueCoin(value);
			value -= coin.GetValue();
			coin.DecCount();
			System.out.println(coin.GetValue() + CURRENCY);
		}
	}
	
	// je nove id napoje unikatni
	private boolean IsDrinkIdUnicate(int id)
	{
		for(int i=0; i < this.n.size(); i++)
		{
			Napoj drink = (Napoj) n.elementAt(i);
			if(drink.GetId() == id) return false;
		}
		return true;
	}
	
	// je nove id mince unikatni
	private boolean IsCoinIdUnicate(int id)
	{
		for(int i=0; i < this.m.size(); i++)
		{
			Mince coin = (Mince) m.elementAt(i);
			if(coin.GetId() == id) return false;
		}
		return true;
	}
	
	/**
     * Zadani vstupu z klavesnice
     *  	  	    
     * @return         vrati cislo, zadane na standardni vstup, v pripade neuspechu vrati -1
     */
	public static int Input()
	{
		int value;						// vysledna hodnota
		byte buffer[] = new byte[64];	// buffer
		int buffer_cnt = 0;				// pocet nactenych znaku
		
		try
		{
			System.out.print(PROMPT);					// vypsani promptu
			buffer_cnt = System.in.read(buffer);		// cteni ze vstupu
			//System.out.write(buffer, 0, buffer_cnt);	// kontrolni vypis
			String s = new String(buffer);		// prevod pole znaku na string
			int len = s.lastIndexOf(13); 		// delka retezce, 13 reprezentuje znak carriage return
			s = s.substring(0,len);				// orezani bilych znaku z retezce
			value = new Integer(s).intValue();	// prevod string na int
		}
		catch (IOException e)
		{
			System.out.println(SEPARATOR);
			System.out.println("ERROR: Chyba ve vstupni/vystupni operaci!");
			value = -1;
		}
		catch (NumberFormatException e)
		{
			System.out.println(SEPARATOR);
			System.out.println("ERROR: Neplatny vstup!");
			value = -1;
		}
		
		return value;
	}
	
	/**
     * Explicitne zastavi cinnost automatu
     */
	public void StopAutomat()
	{
		this.run = false;
	}
	
	/**
     * Spusti automat, v pripade, ze v automatu je aspon 1 typ napoje a aspon 1 typ mince
     */
	public void StartAutomat()
	{
		// inicializace
		Napoj drink = null;					// ukazatel na vybrany napoj
		Mince coin = null;					// ukazatel na vhozenou minci
		int drinkId;						// id vybraneho napoje
		int coinId;							// id vhozene mince
		int cnt;							// pocet vybranych kusu napoju
		int inserted;						// hodnota vhozenych penez
		int overvalue;						// preplatek
		Vector<Integer> coinsinserted = new Vector<Integer>();	// vektor vhozenych minci
		Vector<Mince> push = new Vector<Mince>();				// vektor pro ulozeni stavu minci
			
		// hlavni cyklus automatu
		while(this.run && m.size()>0 && n.size()>0)
		{
			////////////////////////////////////////////////////////////////////////////////////////////////////
			// vyber typu napoje
			////////////////////////////////////////////////////////////////////////////////////////////////////
		
			// vypis napoju
			System.out.println(SEPARATOR);
			PrintDrinks();
			System.out.println(SEPARATOR);
			System.out.println("Zadejte cislo napoje a stisknete enter:");
			drinkId = Input();
			
			// vypnuti automatu a neplatny vstup
			if(drinkId == 0) return;
			else if(drinkId == -1) continue;
			
			// kontrola spravneho vstupu
			drink = GetDrinkById(drinkId);
			if(drink == null)
			{
				System.out.println(SEPARATOR);
				System.out.println("Pozadovany napoj neexistuje!");
				System.out.println("Zadejte prosim svuj pozadavek znovu.");
				continue;
			}
	
			System.out.println(SEPARATOR);
			
			// kontrola dostupnosti napoje
			if(drink.GetCount() <= 0)
			{
				System.out.println("Litujeme, ale napoj " + drink.GetName() + " neni momentalne k dispozici.");
				System.out.println("Zadejte prosim svuj pozadavek znovu.");
				continue;
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			// pocet kusu
			////////////////////////////////////////////////////////////////////////////////////////////////////
			
			System.out.println("Zadejte pocet napoju a stisknete enter (0 pro storno):");
			cnt = Input();
			
			// kontrola dostupnosti daneho poctu napoju
			if(!drink.IsEnoughDrinks(cnt))
			{
				System.out.println(SEPARATOR);
				System.out.println("Litujeme, ale automat muze nabidnout jen " + drink.GetCount() + PIECE + " napoje " + drink.GetName() + ".");
				System.out.println("Zadejte prosim svuj pozadavek znovu.");
				continue;
			}
			else if(cnt <= 0)
			{
				continue;
			}
	
			////////////////////////////////////////////////////////////////////////////////////////////////////
			// platba
			////////////////////////////////////////////////////////////////////////////////////////////////////
			
			System.out.println(SEPARATOR);
			PrintCoins();
			System.out.println(SEPARATOR);
			System.out.println("Vhazujte postupne do automatu jednotlive mince.");
			System.out.println("Zadejte cislo mince a stisknete enter (0 pro storno):");
			inserted = 0;
			coinsinserted.clear();
			
			// cyklus vhazovani minci
			while(true)
			{
				System.out.println("Cena: " + cnt*drink.GetPrice() + CURRENCY + ", vhozeno: " + inserted + CURRENCY);
				
				// vhozenych penez je dostatek
				if(inserted>=cnt*drink.GetPrice()) break;
				
				// vstup
				coinId = Input();
				
				// storno a neplatny vstup
				if(coinId == 0) break;
				else if(coinId == -1) continue;
				
				// kontrola spravneho vstupu
				coin = GetCoinById(coinId);
				if(coin == null)
				{
					System.out.println(SEPARATOR);
					System.out.println("Mince neexistuje!");
					System.out.println("Zadejte prosim svuj pozadavek znovu.");
					continue;
				}
				
				// pricteni penez
				inserted += coin.GetValue();	// citac vhozenych penez v automatu
				coin.IncCount();				// pridani mince do automatu
				coinsinserted.add(coin.GetId());// pridani mince do pole vhozenych minci
			}
			
			// storno aniz by byla vhozena jakakoli mince 
			if(inserted==0) continue;
			
			////////////////////////////////////////////////////////////////////////////////////////////////////
			// vraceni preplatku
			////////////////////////////////////////////////////////////////////////////////////////////////////
			
			// preplatek
			overvalue = inserted - cnt*drink.GetPrice();
			
			// bude nutno vratit penize
			if(overvalue!=0)
			{
				System.out.println(SEPARATOR);
				System.out.println("VRACENI PREPLATKU:");
				coin = null;

				// ulozime si stav minci
				push.clear();
				for(int x = 0; x<m.size(); x++)
				{
					// zkopiruje puvodni vektor minci do noveho pomocneho vektoru
					Mince oldcoin = m.elementAt(x);
					Mince newcoin = new Mince(oldcoin.GetId(), oldcoin.GetValue(), oldcoin.GetCount());
					push.add(newcoin);
				}

				// bude nutno vratit preplatek, zjistime zda ma automat na vraceni
				while(overvalue>0)
				{
					// nalezeni vhodne mince na vraceni
					coin = GetOvervalueCoin(overvalue);
					
					// automat nemuze vratit preplatek
					if(coin == null)
					{
						System.out.println("Omlouvame se, ale automat nemuze vratit preplatek.");
						System.out.println("Vase objednavka se stornuje a penize vam budou vraceny.");
						break;
					}
					else
					{
						overvalue -= coin.GetValue();
						coin.DecCount();
					}
				}

				// vratime zpet stav minci z pomocneho vektoru
				for(int x = 0; x<push.size(); x++)
				{
					Mince a = push.elementAt(x);
					Mince b = m.elementAt(x);
					b.SetCount(a.GetCount());
				}

				// automat muze vratit preplatek
				if(overvalue==0)
				{
					System.out.println("Preplatek cini " + (inserted - cnt*drink.GetPrice()) + CURRENCY + ". Vracim mince:");
					ReturnCoins(inserted - cnt*drink.GetPrice());
				}
				
				// storno z platby nebo storno z nemoznosti vratit preplatek
				if(overvalue<0 || coin == null)
				{
					System.out.println("Nyni vam budou vraceny penize ze storna. Vracim mince:");
					
					//ReturnCoins(inserted);
					
					for(int i=0; i < coinsinserted.size(); i++)
					{
						int id = coinsinserted.elementAt(i);
						coin = GetCoinById(id);
						coin.DecCount();
						System.out.println(coin.GetValue() + CURRENCY);
					}

					continue;
				}
			}

			////////////////////////////////////////////////////////////////////////////////////////////////////
			// vydani napoje
			////////////////////////////////////////////////////////////////////////////////////////////////////
			
			System.out.println(SEPARATOR);
			System.out.println("VYDANI NAPOJE:");
			System.out.println(cnt + PIECE + " " + drink.GetName());
			System.out.println("Odeberte si prosim napoj. Dekujeme za vas nakup.");
			
			// odecteni poctu napoju
			drink.SetCount(drink.GetCount()-cnt);
		}
	}
}
