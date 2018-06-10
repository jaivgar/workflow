package org.hasder.workflowexecutor;

import com.google.gson.Gson;

/**
+---------------+-------+---------+
|          Name | label | Type    |
+---------------+-------+---------+
|     Base Name | bn    | String  |
|     Base Time | bt    | Number  |
|     Base Unit | bu    | String  |
|    Base Value | bv    | Number  |
|      Base Sum | bs    | Number  |
|       Version | bver  | Number  |
|          Name | n     | String  |
|          Unit | u     | String  |
|         Value | v     | Number  |
|  String Value | vs    | String  |
| Boolean Value | vb    | Boolean |
|    Data Value | vd    | String  |
|     Value Sum | s     | Number  |
|          Time | t     | Number  |
|   Update Time | ut    | Number  |
|          Link | l     | String  |
+---------------+-------+---------+
*/

public class SenMLRecord {

		private String bn, bu, n, u, vs, vd, l;
		private Integer bv, bs, bver, v, s;
		private Long bt, t, ut;
		private Boolean vb;
		
		public SenMLRecord() {
			//
		}
		
		public SenMLRecord(String n, String vs) {
			this.n = n;
			this.vs = vs;
		}
		
		public SenMLRecord(String n, int v) {
			this.n = n;
			this.v = v;
		}
		
		public SenMLRecord(String n, int v, String u) {
			this.n = n;
			this.v = v;
			this.u = u;
		}
		
		public SenMLRecord(String n, int v, long t) {
			this.n = n;
			this.v = v;
			this.t = t;
		}
		
		public SenMLRecord(String n, int v, String u, long t) {
			this.n = n;
			this.v = v;
			this.u = u;
			this.t = t;
		}
		
		public String toJSON() {
			return new Gson().toJson(this);
		}
		
		public static SenMLRecord fromJSON(String json) {
			return new Gson().fromJson(json, SenMLRecord.class);
		}

		public String getBn() {
			return bn;
		}

		public void setBn(String bn) {
			this.bn = bn;
		}

		public String getBu() {
			return bu;
		}

		public void setBu(String bu) {
			this.bu = bu;
		}

		public String getN() {
			return n;
		}

		public void setN(String n) {
			this.n = n;
		}

		public String getU() {
			return u;
		}

		public void setU(String u) {
			this.u = u;
		}

		public String getVs() {
			return vs;
		}

		public void setVs(String vs) {
			this.vs = vs;
		}

		public String getVd() {
			return vd;
		}

		public void setVd(String vd) {
			this.vd = vd;
		}

		public String getL() {
			return l;
		}

		public void setL(String l) {
			this.l = l;
		}

		public Integer getBv() {
			return bv;
		}

		public void setBv(Integer bv) {
			this.bv = bv;
		}

		public Integer getBs() {
			return bs;
		}

		public void setBs(Integer bs) {
			this.bs = bs;
		}

		public Integer getBver() {
			return bver;
		}

		public void setBver(Integer bver) {
			this.bver = bver;
		}

		public Integer getV() {
			return v;
		}

		public void setV(Integer v) {
			this.v = v;
		}

		public Integer getS() {
			return s;
		}

		public void setS(Integer s) {
			this.s = s;
		}

		public Long getBt() {
			return bt;
		}

		public void setBt(Long bt) {
			this.bt = bt;
		}

		public Long getT() {
			return t;
		}

		public void setT(Long t) {
			this.t = t;
		}

		public Long getUt() {
			return ut;
		}

		public void setUt(Long ut) {
			this.ut = ut;
		}

		public Boolean getVb() {
			return vb;
		}

		public void setVb(Boolean vb) {
			this.vb = vb;
		}

}