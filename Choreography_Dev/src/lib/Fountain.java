/**
 * 
 */
package lib;

import lib.cannons.*;

/**
 * @author madridf
 *
 */
public class Fountain {
	private Ring r11, r21, r31, r41, r51;
	private Multi m1; private Candalabra c1; private Sweeps s1; //Module 1
	private Ring r12, r22, r32, r42, r52;
	private Multi m2; private Candalabra c2; private Sweeps s2; //Module 2
	private Ring r13, r23, r33, r43, r53;
	private Multi m3; private Candalabra c3; private Sweeps s3; //Module 3
	private Ring r14, r24, r34, r44, r54;
	private Multi m4; private Candalabra c4; private Sweeps s4; //Module 4
	private Ring r15, r25, r35, r45, r55;
	private Multi m5; private Candalabra c5; private Sweeps s5; //Module 5
	private Ring r16, r26, r36, r46, r56;
	private Multi m6; private Candalabra c6; private Sweeps s6; //module 6
	private Ring r17, r27, r37, r47, r57;
	private Multi m7; private Candalabra c7; private Sweeps s7; //Module 7
	private Ring[][] rings;
	private Multi[] multis;
	private Candalabra[] candles;
	private Sweeps[] sweeps;
	private Peacock peacock; private Spout spout; private Bazooka bazooka; private FtCurt ftCurt; private BkCurt bkCurt;
	private Module module1, module2, module3, module4, module5, module6, module7;
	private ModuleGroup A;
	private ModuleGroup B;
	
	public Fountain(){
		initCannonArrays();
		
		initRings();
		
		initMultis();
		
		initCandles();
		
		initSweeps();
		
		initIndependentCannons();
		
		module1 = new Module(1, rings[0], m1, c1, s1);
		module2 = new Module(2, rings[1], m2, c2, s2);
		module3 = new Module(3, rings[2], m3, c3, s3);
		module4 = new Module(4, rings[3], m4, c4, s4);
		module5 = new Module(5, rings[4], m5, c5, s5);
		module6 = new Module(6, rings[5], m6, c6, s6);
		module7 = new Module(7, rings[6], m7, c7, s7);
		
		A = new ModuleGroup(module1, module3, module5, module7);
		B = new ModuleGroup(module2, module4, module6);
	}

	/**
	 * 
	 */
	private void initIndependentCannons() {
		peacock = new Peacock(0); spout = new Spout(0); bazooka = new Bazooka(0); ftCurt = new FtCurt(0); bkCurt = new BkCurt(0);
	}

	/**
	 * 
	 */
	private void initCannonArrays() {
		//Initialize the Rings where r(1,1) is ring size 1, module 1
		rings = new Ring[][]{{r11, r21, r31, r41, r51}, //Module 1
				{r12, r22, r32, r42, r52}, //Module 2
				{r13, r23, r33, r43, r53}, //Module 3
				{r14, r24, r34, r44, r54}, //Module 4
				{r15, r25, r35, r45, r55}, //Module 5
				{r16, r26, r36, r46, r56}, //Module 6
				{r17, r27, r37, r47, r57}};//Module 7
		
		multis = new Multi[]{m1, m2, m3, m4, m5, m6, m7};
		candles = new Candalabra[]{c1, c2, c3, c4, c5, c6, c7};
		sweeps = new Sweeps[]{s1, s2, s3, s4, s5, s6, s7};
	}

	/**
	 * 
	 */
	private void initSweeps() {
		//Initialize the Sweeps
		for(int s=0; s <= 6;  s++) {
				if(s % 2 == 0) {
					sweeps[s] = new Sweeps("B", 0);
				} else {
					sweeps[s] = new Sweeps("A", 0);
				}
		}
	}

	/**
	 * 
	 */
	private void initCandles() {
		//Initialize the Candalabra
		for(int c=0; c <= 6;  c++) {
				if(c % 2 == 0) {
					candles[c] = new Candalabra("B", 0);
				} else {
					candles[c] = new Candalabra("A", 0);
				}
		}
	}

	/**
	 * 
	 */
	private void initMultis() {
		//Initialize the Multis
		for(int m=0; m <= 6;  m++) {
				if(m % 2 == 0) {
					multis[m] = new Multi("B", 0);
				} else {
					multis[m] = new Multi("A", 0);
				}
		}
	}

	/**
	 * 
	 */
	private void initRings() {
		//Initialize the Rings
		for(int m=0; m <= 6;  m++) {
			for(int s = 0; s <= 4; s++)
				if(m % 2 == 0) {
					rings[m][s] = new Ring("B", 0, s + 1);
				} else {
					rings[m][s] = new Ring("A", 0, s + 1);
				}
		}
	}

	/**
	 * @return the r11
	 */
	protected Ring getR11() {
		return r11;
	}

	/**
	 * @param r11 the r11 to set
	 */
	protected void setR11(Ring r11) {
		this.r11 = r11;
	}

	/**
	 * @return the r21
	 */
	protected Ring getR21() {
		return r21;
	}

	/**
	 * @param r21 the r21 to set
	 */
	protected void setR21(Ring r21) {
		this.r21 = r21;
	}

	/**
	 * @return the r31
	 */
	protected Ring getR31() {
		return r31;
	}

	/**
	 * @param r31 the r31 to set
	 */
	protected void setR31(Ring r31) {
		this.r31 = r31;
	}

	/**
	 * @return the r41
	 */
	protected Ring getR41() {
		return r41;
	}

	/**
	 * @param r41 the r41 to set
	 */
	protected void setR41(Ring r41) {
		this.r41 = r41;
	}

	/**
	 * @return the r51
	 */
	protected Ring getR51() {
		return r51;
	}

	/**
	 * @param r51 the r51 to set
	 */
	protected void setR51(Ring r51) {
		this.r51 = r51;
	}

	/**
	 * @return the m1
	 */
	protected Multi getM1() {
		return m1;
	}

	/**
	 * @param m1 the m1 to set
	 */
	protected void setM1(Multi m1) {
		this.m1 = m1;
	}

	/**
	 * @return the c1
	 */
	protected Candalabra getC1() {
		return c1;
	}

	/**
	 * @param c1 the c1 to set
	 */
	protected void setC1(Candalabra c1) {
		this.c1 = c1;
	}

	/**
	 * @return the s1
	 */
	protected Sweeps getS1() {
		return s1;
	}

	/**
	 * @param s1 the s1 to set
	 */
	protected void setS1(Sweeps s1) {
		this.s1 = s1;
	}

	/**
	 * @return the r12
	 */
	protected Ring getR12() {
		return r12;
	}

	/**
	 * @param r12 the r12 to set
	 */
	protected void setR12(Ring r12) {
		this.r12 = r12;
	}

	/**
	 * @return the r22
	 */
	protected Ring getR22() {
		return r22;
	}

	/**
	 * @param r22 the r22 to set
	 */
	protected void setR22(Ring r22) {
		this.r22 = r22;
	}

	/**
	 * @return the r32
	 */
	protected Ring getR32() {
		return r32;
	}

	/**
	 * @param r32 the r32 to set
	 */
	protected void setR32(Ring r32) {
		this.r32 = r32;
	}

	/**
	 * @return the r42
	 */
	protected Ring getR42() {
		return r42;
	}

	/**
	 * @param r42 the r42 to set
	 */
	protected void setR42(Ring r42) {
		this.r42 = r42;
	}

	/**
	 * @return the r52
	 */
	protected Ring getR52() {
		return r52;
	}

	/**
	 * @param r52 the r52 to set
	 */
	protected void setR52(Ring r52) {
		this.r52 = r52;
	}

	/**
	 * @return the m2
	 */
	protected Multi getM2() {
		return m2;
	}

	/**
	 * @param m2 the m2 to set
	 */
	protected void setM2(Multi m2) {
		this.m2 = m2;
	}

	/**
	 * @return the c2
	 */
	protected Candalabra getC2() {
		return c2;
	}

	/**
	 * @param c2 the c2 to set
	 */
	protected void setC2(Candalabra c2) {
		this.c2 = c2;
	}

	/**
	 * @return the s2
	 */
	protected Sweeps getS2() {
		return s2;
	}

	/**
	 * @param s2 the s2 to set
	 */
	protected void setS2(Sweeps s2) {
		this.s2 = s2;
	}

	/**
	 * @return the r13
	 */
	protected Ring getR13() {
		return r13;
	}

	/**
	 * @param r13 the r13 to set
	 */
	protected void setR13(Ring r13) {
		this.r13 = r13;
	}

	/**
	 * @return the r23
	 */
	protected Ring getR23() {
		return r23;
	}

	/**
	 * @param r23 the r23 to set
	 */
	protected void setR23(Ring r23) {
		this.r23 = r23;
	}

	/**
	 * @return the r33
	 */
	protected Ring getR33() {
		return r33;
	}

	/**
	 * @param r33 the r33 to set
	 */
	protected void setR33(Ring r33) {
		this.r33 = r33;
	}

	/**
	 * @return the r43
	 */
	protected Ring getR43() {
		return r43;
	}

	/**
	 * @param r43 the r43 to set
	 */
	protected void setR43(Ring r43) {
		this.r43 = r43;
	}

	/**
	 * @return the r53
	 */
	protected Ring getR53() {
		return r53;
	}

	/**
	 * @param r53 the r53 to set
	 */
	protected void setR53(Ring r53) {
		this.r53 = r53;
	}

	/**
	 * @return the m3
	 */
	protected Multi getM3() {
		return m3;
	}

	/**
	 * @param m3 the m3 to set
	 */
	protected void setM3(Multi m3) {
		this.m3 = m3;
	}

	/**
	 * @return the c3
	 */
	protected Candalabra getC3() {
		return c3;
	}

	/**
	 * @param c3 the c3 to set
	 */
	protected void setC3(Candalabra c3) {
		this.c3 = c3;
	}

	/**
	 * @return the s3
	 */
	protected Sweeps getS3() {
		return s3;
	}

	/**
	 * @param s3 the s3 to set
	 */
	protected void setS3(Sweeps s3) {
		this.s3 = s3;
	}

	/**
	 * @return the r14
	 */
	protected Ring getR14() {
		return r14;
	}

	/**
	 * @param r14 the r14 to set
	 */
	protected void setR14(Ring r14) {
		this.r14 = r14;
	}

	/**
	 * @return the r24
	 */
	protected Ring getR24() {
		return r24;
	}

	/**
	 * @param r24 the r24 to set
	 */
	protected void setR24(Ring r24) {
		this.r24 = r24;
	}

	/**
	 * @return the r34
	 */
	protected Ring getR34() {
		return r34;
	}

	/**
	 * @param r34 the r34 to set
	 */
	protected void setR34(Ring r34) {
		this.r34 = r34;
	}

	/**
	 * @return the r44
	 */
	protected Ring getR44() {
		return r44;
	}

	/**
	 * @param r44 the r44 to set
	 */
	protected void setR44(Ring r44) {
		this.r44 = r44;
	}

	/**
	 * @return the r54
	 */
	protected Ring getR54() {
		return r54;
	}

	/**
	 * @param r54 the r54 to set
	 */
	protected void setR54(Ring r54) {
		this.r54 = r54;
	}

	/**
	 * @return the m4
	 */
	protected Multi getM4() {
		return m4;
	}

	/**
	 * @param m4 the m4 to set
	 */
	protected void setM4(Multi m4) {
		this.m4 = m4;
	}

	/**
	 * @return the c4
	 */
	protected Candalabra getC4() {
		return c4;
	}

	/**
	 * @param c4 the c4 to set
	 */
	protected void setC4(Candalabra c4) {
		this.c4 = c4;
	}

	/**
	 * @return the s4
	 */
	protected Sweeps getS4() {
		return s4;
	}

	/**
	 * @param s4 the s4 to set
	 */
	protected void setS4(Sweeps s4) {
		this.s4 = s4;
	}

	/**
	 * @return the r15
	 */
	protected Ring getR15() {
		return r15;
	}

	/**
	 * @param r15 the r15 to set
	 */
	protected void setR15(Ring r15) {
		this.r15 = r15;
	}

	/**
	 * @return the r25
	 */
	protected Ring getR25() {
		return r25;
	}

	/**
	 * @param r25 the r25 to set
	 */
	protected void setR25(Ring r25) {
		this.r25 = r25;
	}

	/**
	 * @return the r35
	 */
	protected Ring getR35() {
		return r35;
	}

	/**
	 * @param r35 the r35 to set
	 */
	protected void setR35(Ring r35) {
		this.r35 = r35;
	}

	/**
	 * @return the r45
	 */
	protected Ring getR45() {
		return r45;
	}

	/**
	 * @param r45 the r45 to set
	 */
	protected void setR45(Ring r45) {
		this.r45 = r45;
	}

	/**
	 * @return the r55
	 */
	protected Ring getR55() {
		return r55;
	}

	/**
	 * @param r55 the r55 to set
	 */
	protected void setR55(Ring r55) {
		this.r55 = r55;
	}

	/**
	 * @return the m5
	 */
	protected Multi getM5() {
		return m5;
	}

	/**
	 * @param m5 the m5 to set
	 */
	protected void setM5(Multi m5) {
		this.m5 = m5;
	}

	/**
	 * @return the c5
	 */
	protected Candalabra getC5() {
		return c5;
	}

	/**
	 * @param c5 the c5 to set
	 */
	protected void setC5(Candalabra c5) {
		this.c5 = c5;
	}

	/**
	 * @return the s5
	 */
	protected Sweeps getS5() {
		return s5;
	}

	/**
	 * @param s5 the s5 to set
	 */
	protected void setS5(Sweeps s5) {
		this.s5 = s5;
	}

	/**
	 * @return the r16
	 */
	protected Ring getR16() {
		return r16;
	}

	/**
	 * @param r16 the r16 to set
	 */
	protected void setR16(Ring r16) {
		this.r16 = r16;
	}

	/**
	 * @return the r26
	 */
	protected Ring getR26() {
		return r26;
	}

	/**
	 * @param r26 the r26 to set
	 */
	protected void setR26(Ring r26) {
		this.r26 = r26;
	}

	/**
	 * @return the r36
	 */
	protected Ring getR36() {
		return r36;
	}

	/**
	 * @param r36 the r36 to set
	 */
	protected void setR36(Ring r36) {
		this.r36 = r36;
	}

	/**
	 * @return the r46
	 */
	protected Ring getR46() {
		return r46;
	}

	/**
	 * @param r46 the r46 to set
	 */
	protected void setR46(Ring r46) {
		this.r46 = r46;
	}

	/**
	 * @return the r56
	 */
	protected Ring getR56() {
		return r56;
	}

	/**
	 * @param r56 the r56 to set
	 */
	protected void setR56(Ring r56) {
		this.r56 = r56;
	}

	/**
	 * @return the m6
	 */
	protected Multi getM6() {
		return m6;
	}

	/**
	 * @param m6 the m6 to set
	 */
	protected void setM6(Multi m6) {
		this.m6 = m6;
	}

	/**
	 * @return the c6
	 */
	protected Candalabra getC6() {
		return c6;
	}

	/**
	 * @param c6 the c6 to set
	 */
	protected void setC6(Candalabra c6) {
		this.c6 = c6;
	}

	/**
	 * @return the s6
	 */
	protected Sweeps getS6() {
		return s6;
	}

	/**
	 * @param s6 the s6 to set
	 */
	protected void setS6(Sweeps s6) {
		this.s6 = s6;
	}

	/**
	 * @return the r17
	 */
	protected Ring getR17() {
		return r17;
	}

	/**
	 * @param r17 the r17 to set
	 */
	protected void setR17(Ring r17) {
		this.r17 = r17;
	}

	/**
	 * @return the r27
	 */
	protected Ring getR27() {
		return r27;
	}

	/**
	 * @param r27 the r27 to set
	 */
	protected void setR27(Ring r27) {
		this.r27 = r27;
	}

	/**
	 * @return the r37
	 */
	protected Ring getR37() {
		return r37;
	}

	/**
	 * @param r37 the r37 to set
	 */
	protected void setR37(Ring r37) {
		this.r37 = r37;
	}

	/**
	 * @return the r47
	 */
	protected Ring getR47() {
		return r47;
	}

	/**
	 * @param r47 the r47 to set
	 */
	protected void setR47(Ring r47) {
		this.r47 = r47;
	}

	/**
	 * @return the r57
	 */
	protected Ring getR57() {
		return r57;
	}

	/**
	 * @param r57 the r57 to set
	 */
	protected void setR57(Ring r57) {
		this.r57 = r57;
	}

	/**
	 * @return the m7
	 */
	protected Multi getM7() {
		return m7;
	}

	/**
	 * @param m7 the m7 to set
	 */
	protected void setM7(Multi m7) {
		this.m7 = m7;
	}

	/**
	 * @return the c7
	 */
	protected Candalabra getC7() {
		return c7;
	}

	/**
	 * @param c7 the c7 to set
	 */
	protected void setC7(Candalabra c7) {
		this.c7 = c7;
	}

	/**
	 * @return the s7
	 */
	protected Sweeps getS7() {
		return s7;
	}

	/**
	 * @param s7 the s7 to set
	 */
	protected void setS7(Sweeps s7) {
		this.s7 = s7;
	}

	/**
	 * @return the rings
	 */
	protected Ring[][] getRings() {
		return rings;
	}

	/**
	 * @param rings the rings to set
	 */
	protected void setRings(Ring[][] rings) {
		this.rings = rings;
	}

	/**
	 * @return the multis
	 */
	protected Multi[] getMultis() {
		return multis;
	}

	/**
	 * @param multis the multis to set
	 */
	protected void setMultis(Multi[] multis) {
		this.multis = multis;
	}

	/**
	 * @return the candles
	 */
	protected Candalabra[] getCandles() {
		return candles;
	}

	/**
	 * @param candles the candles to set
	 */
	protected void setCandles(Candalabra[] candles) {
		this.candles = candles;
	}

	/**
	 * @return the sweeps
	 */
	protected Sweeps[] getSweeps() {
		return sweeps;
	}

	/**
	 * @param sweeps the sweeps to set
	 */
	protected void setSweeps(Sweeps[] sweeps) {
		this.sweeps = sweeps;
	}

	/**
	 * @return the peacock
	 */
	protected Peacock getPeacock() {
		return peacock;
	}

	/**
	 * @param peacock the peacock to set
	 */
	protected void setPeacock(Peacock peacock) {
		this.peacock = peacock;
	}

	/**
	 * @return the spout
	 */
	protected Spout getSpout() {
		return spout;
	}

	/**
	 * @param spout the spout to set
	 */
	protected void setSpout(Spout spout) {
		this.spout = spout;
	}

	/**
	 * @return the bazooka
	 */
	protected Bazooka getBazooka() {
		return bazooka;
	}

	/**
	 * @param bazooka the bazooka to set
	 */
	protected void setBazooka(Bazooka bazooka) {
		this.bazooka = bazooka;
	}

	/**
	 * @return the ftCurt
	 */
	protected FtCurt getFtCurt() {
		return ftCurt;
	}

	/**
	 * @param ftCurt the ftCurt to set
	 */
	protected void setFtCurt(FtCurt ftCurt) {
		this.ftCurt = ftCurt;
	}

	/**
	 * @return the bkCurt
	 */
	protected BkCurt getBkCurt() {
		return bkCurt;
	}

	/**
	 * @param bkCurt the bkCurt to set
	 */
	protected void setBkCurt(BkCurt bkCurt) {
		this.bkCurt = bkCurt;
	}

	/**
	 * @return the module1
	 */
	protected Module getModule1() {
		return module1;
	}

	/**
	 * @param module1 the module1 to set
	 */
	protected void setModule1(Module module1) {
		this.module1 = module1;
	}

	/**
	 * @return the module2
	 */
	protected Module getModule2() {
		return module2;
	}

	/**
	 * @param module2 the module2 to set
	 */
	protected void setModule2(Module module2) {
		this.module2 = module2;
	}

	/**
	 * @return the module3
	 */
	protected Module getModule3() {
		return module3;
	}

	/**
	 * @param module3 the module3 to set
	 */
	protected void setModule3(Module module3) {
		this.module3 = module3;
	}

	/**
	 * @return the module4
	 */
	protected Module getModule4() {
		return module4;
	}

	/**
	 * @param module4 the module4 to set
	 */
	protected void setModule4(Module module4) {
		this.module4 = module4;
	}

	/**
	 * @return the module5
	 */
	protected Module getModule5() {
		return module5;
	}

	/**
	 * @param module5 the module5 to set
	 */
	protected void setModule5(Module module5) {
		this.module5 = module5;
	}

	/**
	 * @return the module6
	 */
	protected Module getModule6() {
		return module6;
	}

	/**
	 * @param module6 the module6 to set
	 */
	protected void setModule6(Module module6) {
		this.module6 = module6;
	}

	/**
	 * @return the module7
	 */
	protected Module getModule7() {
		return module7;
	}

	/**
	 * @param module7 the module7 to set
	 */
	protected void setModule7(Module module7) {
		this.module7 = module7;
	}

	/**
	 * @return the a
	 */
	public ModuleGroup getA() {
		return A;
	}

	/**
	 * @param a the a to set
	 */
	protected void setA(ModuleGroup a) {
		A = a;
	}

	/**
	 * @return the b
	 */
	public ModuleGroup getB() {
		return B;
	}

	/**
	 * @param b the b to set
	 */
	protected void setB(ModuleGroup b) {
		B = b;
	}
}
