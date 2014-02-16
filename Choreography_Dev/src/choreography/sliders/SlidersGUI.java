/**
 * 
 */
package choreography.sliders;

import lib.Fountain;
import lib.Module;
import lib.cannons.Bazooka;
import lib.cannons.BkCurt;
import lib.cannons.Candalabra;
import lib.cannons.FtCurt;
import lib.cannons.Multi;
import lib.cannons.Peacock;
import lib.cannons.Ring;
import lib.cannons.Spout;
import lib.cannons.Sweeps;

/**
 * @author madridf
 *
 */
public class SlidersGUI {
	private Ring r11, r21, r31, r41, r51;
	private Multi m1; Candalabra c1; Sweeps s1; //module 1
	private Ring r12, r22, r32, r42, r52;
	private Multi m2; Candalabra c2; Sweeps s2; //module 2
	private Ring r13, r23, r33, r43, r53;
	private Multi m3; Candalabra c3; Sweeps s3; //module 3
	private Ring r14, r24, r34, r44, r54;
	private Multi m4; Candalabra c4; Sweeps s4; //module 4
	private Ring r15, r25, r35, r45, r55;
	private Multi m5; Candalabra c5; Sweeps s5; //module 5
	private Ring r16, r26, r36, r46, r56;
	private Multi m6; Candalabra c6; Sweeps s6; //module 6
	private Ring r17, r27, r37, r47, r57;
	private Multi m7; Candalabra c7; Sweeps s7; //module 7
	private Peacock peacock; 
	private Bazooka bazooka;
	private BkCurt bkCurt;
	private FtCurt ftCurt;
	private Spout spout;
	
	//TODO instantiate all required cannons, modules, module groups.
	//TODO Fire them off to the SliderController
	
	public SlidersGUI(Fountain foutain){
		Module module1 = new Module(1 ,r11, r21, r31, r41, r51, m1, c1, s1);
		Module module2 = new Module(2 ,r12, r22, r32, r42, r52, m2, c2, s2);
		Module module3 = new Module(3 ,r13, r23, r33, r43, r53, m3, c3, s3);
		Module module4 = new Module(4 ,r14, r24, r34, r44, r54, m4, c4, s4);
		Module module5 = new Module(5 ,r15, r25, r35, r45, r55, m5, c5, s5);
		Module module6 = new Module(6 ,r16, r26, r36, r46, r56, m6, c6, s6);
		Module module7 = new Module(7 ,r17, r27, r37, r47, r57, m7, c7, s7);
		peacock = new Peacock(0);
		bazooka = new Bazooka(0);
		bkCurt = new BkCurt(0);
		ftCurt = new FtCurt(0);
		spout = new Spout(0);
	}
}
