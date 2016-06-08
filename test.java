package compil;









public class test {

	


	public static void main(String[] args){
		System.out.println ("la couleur 0 représente le spill");
		System.out.println ("il y a 3 essais, le graphe du cours avec 3 couleurs différentes, le graphe du cours avec 2 couleurs différentes, et un graphe complet à 4 sommets et 4 couleurs");
		sommet[] sommet = new sommet[6];
		for(int i=0; i<6; i++)
		{
		sommet[i] = new sommet(i);
		}
		interference[] inter = new interference[7];
		inter[0] = new interference(sommet[0], sommet[1]);
		inter[1] = new interference(sommet[0], sommet[5]);
		inter[2] = new interference(sommet[0], sommet[4]);
		inter[3] = new interference(sommet[1], sommet[4]);
		inter[4] = new interference(sommet[2], sommet[5]);
		inter[5] = new interference(sommet[3], sommet[1]);
		inter[6] = new interference(sommet[3], sommet[5]);
		preference[] pref = new preference[1];
		pref[0] = new preference(sommet[0], sommet[2]);
		sommet = compilateur(sommet, inter, pref, 3);
		for(int i=0; i<6;i++)
		{
			System.out.println ("sommet : " + sommet[i].name + " couleur : " + sommet[i].color);
		}
		System.out.println ("nouvel essai");
		sommet = compilateur(sommet, inter, pref, 2);
		for(int i=0; i<6;i++)
		{
			System.out.println ("sommet : " + sommet[i].name + " couleur : " + sommet[i].color);
		}
		
		sommet[] sommet2 = new sommet[4];
		for(int i=0; i<4; i++)
		{
		sommet2[i] = new sommet(i);
		}
		
		interference[] inter2 = new interference[6];
		inter2[0] = new interference(sommet2[0], sommet2[1]);
		inter2[1] = new interference(sommet2[0], sommet2[2]);
		inter2[2] = new interference(sommet2[1], sommet2[2]);
		inter2[3] = new interference(sommet2[1], sommet2[3]);
		inter2[4] = new interference(sommet2[0], sommet2[3]);
		inter2[5] = new interference(sommet2[2], sommet2[3]);
		
		preference[] pref2 = new preference[1];
		pref[0] = new preference(sommet2[0], sommet2[2]);
		sommet2 = compilateur(sommet2, inter2, pref2, 4);
		System.out.println ("nouvel essai");
		for(int i=0; i<4;i++)
		{
			System.out.println ("sommet : " + sommet2[i].name + " couleur : " + sommet2[i].color);
		}
	}
	
	public static sommet[] compilateur (sommet[] sommet, interference[] inter, preference[] pref, int k) {
        sommet[] somRep = new sommet[sommet.length] ;
		if (sommet.length==1){
			somRep = colorier(0, sommet, inter, pref, k);
		}
		else{
		int i = 0;
        interference nminter = new interference();
        interference[] inter3;
        sommet[] som2 = new sommet[sommet.length - 1] ;

        boolean trouve=false;
        while( !trouve && i<sommet.length )
        {
            if(compter(sommet[i],inter) < k)
            {
                trouve = true;
                for(int y=0; y<i;y++)
                {
                    som2[y] = sommet[y];
                }
                for(int y=i+1; y<sommet.length ;y++)
                {
                    som2[y-1] = sommet[y];
                }
                
                int j=0,l=0;
                inter3= new interference[inter.length - compter(sommet[i],inter)];
                while(j<inter.length){
                    nminter=inter[j];
                    if(nminter.a.name !=sommet[i].name && nminter.b.name !=sommet[i].name){
                        inter3[l]=nminter;
                        l+=1;	
                    }
                    j+=1;
                }
                som2 = compilateur(som2,inter3,pref,k);
                for(int y=0; y<i;y++)
                {
                    sommet[y] = som2[y];
                }
                for(int y=i; y<som2.length;y++)
                {
                    sommet[y+1] = som2[y];
                }

                somRep = colorier(i, sommet, inter, pref, k);
                
                
                
                
                
            }
            else
            {
                
                i = i + 1;
            }
        }
        
        if(!trouve){
            int max=0;
            int numSom=0;
            for(i=0;i<sommet.length;i++){
                if(compter(sommet[i],inter) > max)
                {
                    max = compter(sommet[i],inter);
                    numSom=i;
                }
                
            }
            i = numSom;
            for(int y=0; y<i;y++)
            {
                som2[y] = sommet[y];
            }
            for(int y=i+1; y<sommet.length ;y++)
            {
                som2[y-1] = sommet[y];
            }
            
            int j=0,l=0;
            inter3= new interference[inter.length - compter(sommet[i],inter)];
            while(j<inter.length){
                nminter=inter[j];
                if(nminter.a.name!=sommet[i].name && nminter.b.name!=sommet[i].name){
                    inter3[l]=nminter;
                    l+=1;
                }
                j+=1;
            }
            som2 = compilateur(som2,inter3,pref,k);
            for(int y=0; y<i;y++)
            {
                sommet[y] = som2[y];
            }
            for(int y=i; y<som2.length;y++)
            {
                sommet[y+1] = som2[y];
            }
            somRep = sommet;
            
        }
		}
        return somRep;
    }

    
    
    
    
    
    
    
    
    
    
    public static sommet[] colorier(int som,sommet[] sommet, interference[] inter, preference[] pref, int k)
    {
        if(sommet.length == 1)
        {
            sommet[som].color=k;
        }
        else
        {	
        	
        	int[] listeColor = new int[k];
        	for(int i = 0; i<k;i++)
        	{
        	listeColor[i] = 0;
        	}
            for(int y=0; y<inter.length; y++)
            {
            	int j=0;
            	boolean trouve=false;

                if(inter[y].a.name==sommet[som].name)
                {
                	while (listeColor[j] != 0 && !trouve)
                	{
                		if (inter[y].b.color==listeColor[j])
                              trouve = true;
                		else 
                			j++;
                	}
                	if(!trouve)
                		listeColor[j] = inter[y].b.color;
              
                }
                else if (inter[y].b.name==sommet[som].name){
                	while (listeColor[j] != 0 && !trouve)
                	{
                		if (inter[y].a.color==listeColor[j])
                              trouve = true;
                		else 
                			j++;
                	}
                	if(!trouve)
                		listeColor[j] = inter[y].a.color;
                }
                
            }
            boolean dispo = true;
            for (int j=1; j<=k ; j++){
            	for (int i=0;i<k;i++){
            		if(listeColor[i]==j)
            			dispo=false;
            	}
            	if(dispo)
            		sommet[som].color=j;
            }
            
            
        }
        
        return sommet;
        
    }
    
    
    public static int compter(sommet a, interference[] b)
    {
        int n = 0;
        for(int i = 0; i<b.length;i++)
        {
        	if (a.name==b[i].a.name || a.name==b[i].b.name)
        	{
        		n++;
        	}
        }
        return n;
    }
}

