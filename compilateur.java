
    
    
    public class interference {
        
        Sommet a, b;
        
        public interference(Sommet a, Sommet b)
        {
            this.a = a;
            this.b = b;
        }
        
        public interference(){
            a=null;
            b=null;
        }
    }
    
    public class sommet {
        String name;
        int color;
        
        public sommet(String name)
        {
            this.name = name;
            this.color = 0;
        }
        public sommet(String name, int color)
        {
            this.name = name;
            this.color = color;
        }
    }
    
    public class preference {
        
        String a, b;
        
        public preference(String a, String b)
        {
            this.a = a;
            this.b = b;
        }
    }


public class final{
    public sommet[] compilateur (sommet[] sommet, interference[] inter, preference[] pref, int k) {
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
                    if(nminter.a!=sommet[i].name && nminter.b!=sommet[i].name){
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

                return colorier(i, sommet, inter, pref, k);
                
                
                
                
                
            }
            else
            {
                
                i = i + 1;
            }
        }
        
        if(!trouve){
            int max=0;
            int numSom=0;
            for(int i=0;i<sommet.length;i++){
                if(compter(sommet[y],inter) > max)
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
                if(nminter.a!=sommet[i].name && nminter.b!=sommet[i].name){
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
            return sommet;
            
        }
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public sommet[] colorier(int i,sommet[] sommet, interference[] inter, preference[] pref, int k)
    {
        if(sommet.length == 1)
        {
            sommet[i]=1;
        }
        else
        {
            ArrayList<int> ListeColor;
            for(int y=0; y<inter.length; y++)
            {
                if(inter[y].a.name==sommet[i].name)
                {
                    if (ListeColor.existing(inter[y].b.color))
                        ListeColor.add(inter[y].b.color);
                }
                else if (inter[y].b.name==sommet[i].name){
                    if (ListeColor.existing(inter[y].a.color))
                        ListeColor.add(inter[y].a.color);

                }
                
            }
            
            for (int j=1; j<=k ; i++){
            
                if (!ListeColor.existing(j)){
                    sommet[i].color=j;
                }
            }
            
            
        }
        
        return sommet;
        
    }
    
    public static void main(String[] args){
        
    }
    
    public int compter(sommet a, interference[] b)
    {
        return 0;
    }
}

