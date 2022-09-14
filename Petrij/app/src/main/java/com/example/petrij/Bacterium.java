package com.example.petrij;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "bacterium")
public class Bacterium {

    @PrimaryKey(autoGenerate = true)
    private Integer _id;

    private String name;  //1

    private int imageId;   //9
    private boolean gram; //4
    private boolean waterc;    //6
    private boolean intracellular; //5
    private String shape; //2
    private String aero;  //3
    private boolean agarb;   //7
    private String zanimljivo;  //8

    @Ignore
    public static Bacterium[] bacteria = {
            new Bacterium("Streptococcus pyogenus", R.drawable.strep, true, true,true,"kok","fakultativni anaerob", true,"U odgovarajućim uvjetima uzrokuje necrotising fascitis.Spada u flesh eating bakterije" ),
            new Bacterium("Staphlococcus bejbe", R.drawable.staph , true,true,true,"Kok" ,"fakultativni anaerob",false,"Velik broj ljudi u populaciji je asimptomatski carrier ovog patogena i to predstavlja javnozdravstveni problem" ),
            new Bacterium("Staphilococus aureus",  R.drawable.borrelia , false,false,true,"Spiril", "obligatni anaerob",false,"Eksperimentira se sa novim načinom ubijanja ove bakterije u zaraženih, liječenjem toplinom ")
    };

    public Bacterium(String name,int imageId,boolean gram, boolean waterc, boolean intracellular, String shape,String aero,  boolean agarb , String zanimljivo) {
        this.name = name;
        this.shape= shape;
        this.aero=aero;
        this.gram = gram;
        this.intracellular=intracellular;
        this.waterc= waterc;
        this.agarb=agarb;

        this.zanimljivo = zanimljivo;
        this.imageId = imageId;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getAero() {
        return aero;
    }

    public void setAero(String aero) {
        this.aero= aero;
    }

    public Boolean getGram(){return gram ; }

    public void setGram(boolean gram){this.gram = gram;}

    public Boolean getIntracellular(){return intracellular;}

    public void setIntracellular(boolean intracellular){ this.intracellular = intracellular;}

    public Boolean getWaterc(){return waterc;}

    public void setWaterc(boolean gram){this.waterc = waterc;}

    public Boolean getAgarb(){return agarb;};

    public void setAgarb(boolean gram){this.agarb = agarb;}

    public String getZanimljivo() {
        return zanimljivo;
    }
    public void setZanimljivo(String zanimljivo) {
        this.zanimljivo = zanimljivo;}

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
