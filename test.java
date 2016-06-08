import java.util.ArrayList;









public class test {

	
	public static void main(String[] args){
		
		sommet[] sommet = new sommet[3];
		for(int i=0; i<3; i++)
		{
		sommet[i] = new sommet(i);
		}
		interference[] inter = new interference[3];
		inter[0] = new interference(sommet[0], sommet[1]);
		inter[1] = new interference(sommet[1], sommet[2]);
		inter[2] = new interference(sommet[0], sommet[2]);
		preference[] pref = new preference[1];
		pref[0] = new preference(sommet[0], sommet[2]);
		sommet = compilateur(sommet, inter, pref, 3);
		for(int i=0; i<3;i++)
		{
			System.out.println ("sommet : " + sommet[i].name + " couleur : " + sommet[i].color);
		}
	}
	
	public static sommet[] compilateur (sommet[] sommet, interference[] inter, preference[] pref, int k) {
        int i = 0;
        interference nminter = new interference();
        interference[] inter3;
        sommet[] som2 = new sommet[sommet.length - 1] ;
        sommet[] somRep = new sommet[sommet.length] ;
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
                for(int y=i; y<som2.length + 1;y++)
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
        return somRep;
    }

    
    
    
    
    
    
    
    
    
    
    public static sommet[] colorier(int i,sommet[] sommet, interference[] inter, preference[] pref, int k)
    {
        if(sommet.length == 1)
        {
            sommet[i].color=1;
        }
        else
        {
            ArrayList<Integer> ListeColor;
            ListeColor = null;
            for(int y=0; y<inter.length; y++)
            {
                if(inter[y].a.name==sommet[i].name)
                {
                    if (!ListeColor.contains(inter[y].b.color))
                        ListeColor.add(inter[y].b.color);
                }
                else if (inter[y].b.name==sommet[i].name){
                    if (!ListeColor.contains(inter[y].a.color))
                        ListeColor.add(inter[y].a.color);

                }
                
            }
            
            for (int j=1; j<=k ; i++){
            
                if (!ListeColor.contains(j)){
                    sommet[i].color=j;
                }
            }
            
            
        }
        
        return sommet;
        
    }
    
    
    public static int compter(sommet a, interference[] b)
    {
        int n = 0;
        for(int i = 0; i<b.length;i++)
        {
        	if (a.name!=b[i].a.name && a.name!=b[i].b.name)
        	{
        		n++;
        	}
        }
        return n;
    }
}
