package com.spg.chapter3;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.spg.chapter3.house.DetachedHouse;
import com.spg.chapter3.house.Mansion;
import com.spg.chapter3.house.StudioFlat;
import com.spg.chapter3.house.TerraceHouse;
import com.spg.chapter3.neighbourhood.AncientIndianBurialGround;
import com.spg.chapter3.neighbourhood.CountrySide;
import com.spg.chapter3.neighbourhood.InnerCity;
import com.spg.chapter3.owner.DIYGuy;
import com.spg.chapter3.owner.Hoarder;
import com.spg.chapter3.owner.NeatFreak;
import com.spg.chapter3.work.Extension;
import com.spg.chapter3.work.KnockedAHole;
import com.spg.chapter3.work.UglyWallpaper;

public class HouseValuator {

	public static void main( String[] args ) {

		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		// A detached house with an extension, in the countryside, with a owner interested in DIY
		House house1 = new DetachedHouse();
		house1.setNumberOfRooms( 8 );
		House house1Total = new Extension( new CountrySide( new DIYGuy( house1 ) ) );
		
		System.out.println( "First house has price £" + df.format( house1Total.getPrice() ) );
		
		// A terrace house owned by a hoarder, with a hole knocked in between rooms to make even more hoarding room
		House house2 = new TerraceHouse();
		house2.setNumberOfRooms( 6 );
		House house2Total = new KnockedAHole( new Hoarder( house2 ) );
		
		System.out.println( "Second house has price £" + df.format( house2Total.getPrice() ) );
		
		// A studio flat house with ugly wallpaper, in the inner city
		House house3 = new StudioFlat();
		house3.setNumberOfRooms( 2 );
		House house3Total = new UglyWallpaper( new InnerCity( house3 ) );
		
		System.out.println( "Third house has price £" + df.format( house3Total.getPrice() ) );

		// A mansion owned by a neat freak, with a very interesting history
		House house4 = new Mansion();
		house4.setNumberOfRooms( 20 );
		House house4Total = new NeatFreak( new AncientIndianBurialGround( house4 ) );
		
		System.out.println( "Fourth house has price £" + df.format( house4Total.getPrice() ) );

	}

}
