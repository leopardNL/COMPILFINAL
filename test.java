package compil;









public class test {

	


	public static void main(String[] args){
		
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
            for(int y=i; y<som2.length + 1;y++)
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

