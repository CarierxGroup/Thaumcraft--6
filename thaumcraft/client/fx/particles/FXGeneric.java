/*     */ package thaumcraft.client.fx.particles;public class FXGeneric extends Particle { boolean doneFrames; boolean flipped; double windX; double windZ; int layer; float dr; float dg; float db;
/*     */   boolean loop;
/*     */   float rotationSpeed;
/*     */   int startParticle;
/*     */   int numParticles;
/*     */   int particleInc;
/*     */   float[] scaleKeys;
/*     */   float[] scaleFrames;
/*     */   float[] alphaKeys;
/*     */   float[] alphaFrames;
/*     */   double slowDown;
/*     */   float randomX;
/*     */   float randomY;
/*     */   float randomZ;
/*     */   int[] finalFrames;
/*     */   boolean angled;
/*     */   float angleYaw;
/*     */   float anglePitch;
/*     */   int gridSize;
/*     */   
/*  21 */   public FXGeneric(World world, double x, double y, double z, double xx, double yy, double zz) { super(world, x, y, z, xx, yy, zz);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.doneFrames = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     this.flipped = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 253 */     this.layer = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     this.dr = 0.0F;
/* 275 */     this.dg = 0.0F;
/* 276 */     this.db = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     this.loop = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 302 */     this.rotationSpeed = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 308 */     this.startParticle = 0;
/* 309 */     this.numParticles = 1;
/* 310 */     this.particleInc = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 326 */     this.scaleKeys = new float[] { 1.0F };
/* 327 */     this.scaleFrames = new float[] { 0.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 334 */     this.alphaKeys = new float[] { 1.0F };
/* 335 */     this.alphaFrames = new float[] { 0.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 349 */     this.slowDown = 0.9800000190734863D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 363 */     this.finalFrames = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 369 */     this.angled = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 391 */     this.gridSize = 64; func_187115_a(0.1F, 0.1F); func_187109_b(x, y, z); this.field_187123_c = this.field_187126_f; this.field_187124_d = this.field_187127_g; this.field_187125_e = this.field_187128_h; this.field_187123_c = x; this.field_187124_d = y; this.field_187125_e = z; this.field_70548_b = 0.0F; this.field_70549_c = 0.0F; this.field_187129_i = xx; this.field_187130_j = yy; this.field_187131_k = zz; } void calculateFrames() { this.doneFrames = true; if (this.alphaKeys == null) func_82338_g(1.0F);  this.alphaFrames = new float[this.field_70547_e + 1]; float inc = (this.alphaKeys.length - 1) / this.field_70547_e; float is = 0.0F; for (int a = 0; a <= this.field_70547_e; a++) { int isF = MathHelper.func_76141_d(is); float diff, diff = (isF < this.alphaKeys.length - 1) ? (diff = this.alphaKeys[isF + 1] - this.alphaKeys[isF]) : 0.0F; float pa = is - isF; this.alphaFrames[a] = this.alphaKeys[isF] + diff * pa; is += inc; }  if (this.scaleKeys == null) setScale(new float[] { 1.0F });  this.scaleFrames = new float[this.field_70547_e + 1]; inc = (this.scaleKeys.length - 1) / this.field_70547_e; is = 0.0F; for (int a = 0; a <= this.field_70547_e; a++) { int isF = MathHelper.func_76141_d(is); float diff, diff = (isF < this.scaleKeys.length - 1) ? (diff = this.scaleKeys[isF + 1] - this.scaleKeys[isF]) : 0.0F; float pa = is - isF; this.scaleFrames[a] = this.scaleKeys[isF] + diff * pa; is += inc; }  } public void func_189213_a() { if (!this.doneFrames) calculateFrames();  this.field_187123_c = this.field_187126_f; this.field_187124_d = this.field_187127_g; this.field_187125_e = this.field_187128_h; if (this.field_70546_d++ >= this.field_70547_e) func_187112_i();  this.field_190015_G = this.field_190014_F; this.field_190014_F += 3.1415927F * this.rotationSpeed * 2.0F; this.field_187130_j -= 0.04D * this.field_70545_g; func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k); this.field_187129_i *= this.slowDown; this.field_187130_j *= this.slowDown; this.field_187131_k *= this.slowDown; this.field_187129_i += this.field_187122_b.field_73012_v.nextGaussian() * this.randomX; this.field_187130_j += this.field_187122_b.field_73012_v.nextGaussian() * this.randomY; this.field_187131_k += this.field_187122_b.field_73012_v.nextGaussian() * this.randomZ; this.field_187129_i += this.windX; this.field_187131_k += this.windZ; if (this.field_187132_l && this.slowDown != 1.0D) { this.field_187129_i *= 0.699999988079071D; this.field_187131_k *= 0.699999988079071D; }  } public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { if (this.loop) { func_70536_a(this.startParticle + this.field_70546_d / this.particleInc % this.numParticles); } else { float fs = this.field_70546_d / this.field_70547_e; func_70536_a((int)(this.startParticle + Math.min(this.numParticles * fs, (this.numParticles - 1)))); }  if (this.finalFrames != null && this.finalFrames.length > 0 && this.field_70546_d > this.field_70547_e - this.finalFrames.length) { int frame = this.field_70547_e - this.field_70546_d; if (frame < 0) frame = 0;  func_70536_a(this.finalFrames[frame]); }  this.field_82339_as = (this.alphaFrames.length <= 0) ? 0.0F : this.alphaFrames[Math.min(this.field_70546_d, this.alphaFrames.length - 1)]; this.field_70544_f = (this.scaleFrames.length <= 0) ? 0.0F : this.scaleFrames[Math.min(this.field_70546_d, this.scaleFrames.length - 1)]; draw(wr, entity, f, f1, f2, f3, f4, f5); } public boolean isFlipped() { return this.flipped; } public void setFlipped(boolean flip) { this.flipped = flip; } public void draw(BufferBuilder wr, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) { float tx1 = this.field_94054_b / this.gridSize; float tx2 = tx1 + 1.0F / this.gridSize; float ty1 = this.field_94055_c / this.gridSize; float ty2 = ty1 + 1.0F / this.gridSize; float ts = 0.1F * this.field_70544_f; if (this.field_187119_C != null) { tx1 = this.field_187119_C.func_94209_e(); tx2 = this.field_187119_C.func_94212_f(); ty1 = this.field_187119_C.func_94206_g(); ty2 = this.field_187119_C.func_94210_h(); }  if (this.flipped) { float t = tx1; tx1 = tx2; tx2 = t; }  float fs = MathHelper.func_76131_a((this.field_70546_d + partialTicks) / this.field_70547_e, 0.0F, 1.0F); float pr = this.field_70552_h + (this.dr - this.field_70552_h) * fs; float pg = this.field_70553_i + (this.dg - this.field_70553_i) * fs; float pb = this.field_70551_j + (this.db - this.field_70551_j) * fs; int i = func_189214_a(partialTicks); int j = i >> 16 & 0xFFFF; int k = i & 0xFFFF; float f5 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * partialTicks - field_70556_an); float f6 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * partialTicks - field_70554_ao); float f7 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * partialTicks - field_70555_ap); if (this.angled) { Tessellator.func_178181_a().func_78381_a(); GL11.glPushMatrix(); GL11.glTranslated(f5, f6, f7); GL11.glRotatef(-this.angleYaw + 90.0F, 0.0F, 1.0F, 0.0F); GL11.glRotatef(this.anglePitch + 90.0F, 1.0F, 0.0F, 0.0F); if (this.field_190014_F != 0.0F) { float f8 = this.field_190014_F + (this.field_190014_F - this.field_190015_G) * partialTicks; GL11.glRotated(f8 * 57.29577951308232D, 0.0D, 0.0D, 1.0D); }  wr.func_181668_a(7, DefaultVertexFormats.field_181704_d); wr.func_181662_b(-ts, -ts, 0.0D).func_187315_a(tx2, ty2).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d(); wr.func_181662_b(-ts, ts, 0.0D).func_187315_a(tx2, ty1).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d(); wr.func_181662_b(ts, ts, 0.0D).func_187315_a(tx1, ty1).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d(); wr.func_181662_b(ts, -ts, 0.0D).func_187315_a(tx1, ty2).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d(); Tessellator.func_178181_a().func_78381_a(); GL11.glPopMatrix(); wr.func_181668_a(7, DefaultVertexFormats.field_181704_d); } else { Vec3d[] avec3d = { new Vec3d((-rotationX * ts - rotationXY * ts), (-rotationZ * ts), (-rotationYZ * ts - rotationXZ * ts)), new Vec3d((-rotationX * ts + rotationXY * ts), (rotationZ * ts), (-rotationYZ * ts + rotationXZ * ts)), new Vec3d((rotationX * ts + rotationXY * ts), (rotationZ * ts), (rotationYZ * ts + rotationXZ * ts)), new Vec3d((rotationX * ts - rotationXY * ts), (-rotationZ * ts), (rotationYZ * ts - rotationXZ * ts)) }; if (this.field_190014_F != 0.0F) { float f8 = this.field_190014_F + (this.field_190014_F - this.field_190015_G) * partialTicks; float f9 = MathHelper.func_76134_b(f8 * 0.5F); float f10 = MathHelper.func_76126_a(f8 * 0.5F) * (float)field_190016_K.field_72450_a; float f11 = MathHelper.func_76126_a(f8 * 0.5F) * (float)field_190016_K.field_72448_b; float f12 = MathHelper.func_76126_a(f8 * 0.5F) * (float)field_190016_K.field_72449_c; Vec3d vec3d = new Vec3d(f10, f11, f12); for (int l = 0; l < 4; l++) avec3d[l] = vec3d.func_186678_a(2.0D * avec3d[l].func_72430_b(vec3d)).func_178787_e(avec3d[l].func_186678_a((f9 * f9) - vec3d.func_72430_b(vec3d))).func_178787_e(vec3d.func_72431_c(avec3d[l]).func_186678_a((2.0F * f9)));  }  wr.func_181662_b(f5 + (avec3d[0]).field_72450_a, f6 + (avec3d[0]).field_72448_b, f7 + (avec3d[0]).field_72449_c).func_187315_a(tx2, ty2).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d(); wr.func_181662_b(f5 + (avec3d[1]).field_72450_a, f6 + (avec3d[1]).field_72448_b, f7 + (avec3d[1]).field_72449_c).func_187315_a(tx2, ty1).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d(); wr.func_181662_b(f5 + (avec3d[2]).field_72450_a, f6 + (avec3d[2]).field_72448_b, f7 + (avec3d[2]).field_72449_c).func_187315_a(tx1, ty1).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d(); wr.func_181662_b(f5 + (avec3d[3]).field_72450_a, f6 + (avec3d[3]).field_72448_b, f7 + (avec3d[3]).field_72449_c).func_187315_a(tx1, ty2).func_181666_a(pr, pg, pb, this.field_82339_as).func_187314_a(j, k).func_181675_d(); }  } public void setWind(double d) { int m = this.field_187122_b.func_72853_d(); Vec3d vsource = new Vec3d(0.0D, 0.0D, 0.0D); Vec3d vtar = new Vec3d(0.1D, 0.0D, 0.0D); vtar = Utils.rotateAroundY(vtar, (m * (40 + this.field_187122_b.field_73012_v.nextInt(10))) / 180.0F * 3.1415927F); Vec3d vres = vsource.func_72441_c(vtar.field_72450_a, vtar.field_72448_b, vtar.field_72449_c); this.windX = vres.field_72450_a * d; this.windZ = vres.field_72449_c * d; } public FXGeneric(World world, double x, double y, double z) { super(world, x, y, z, 0.0D, 0.0D, 0.0D); this.doneFrames = false; this.flipped = false; this.layer = 0; this.dr = 0.0F; this.dg = 0.0F; this.db = 0.0F; this.loop = false; this.rotationSpeed = 0.0F; this.startParticle = 0; this.numParticles = 1; this.particleInc = 1; this.scaleKeys = new float[] { 1.0F }; this.scaleFrames = new float[] { 0.0F }; this.alphaKeys = new float[] { 1.0F }; this.alphaFrames = new float[] { 0.0F }; this.slowDown = 0.9800000190734863D; this.finalFrames = null; this.angled = false; this.gridSize = 64; func_187115_a(0.1F, 0.1F); func_187109_b(x, y, z); this.field_187123_c = this.field_187126_f; this.field_187124_d = this.field_187127_g; this.field_187125_e = this.field_187128_h; this.field_187123_c = x; this.field_187124_d = y; this.field_187125_e = z; this.field_70548_b = 0.0F; this.field_70549_c = 0.0F; }
/*     */   public void setLayer(int layer) { this.layer = layer; }
/*     */   public void func_70538_b(float particleRedIn, float particleGreenIn, float particleBlueIn) { super.func_70538_b(particleRedIn, particleGreenIn, particleBlueIn); this.dr = particleRedIn; this.dg = particleGreenIn; this.db = particleBlueIn; } public void setRBGColorF(float particleRedIn, float particleGreenIn, float particleBlueIn, float r2, float g2, float b2) { super.func_70538_b(particleRedIn, particleGreenIn, particleBlueIn); this.dr = r2; this.dg = g2;
/* 394 */     this.db = b2; } public int func_70537_b() { return this.layer; } public void setLoop(boolean loop) { this.loop = loop; } public void setRotationSpeed(float rot) { this.rotationSpeed = (float)(rot * 0.017453292519943D); } public void setGridSize(int gridSize) { this.gridSize = gridSize; } public void setRotationSpeed(float start, float rot) { this.field_190014_F = (float)(start * Math.PI * 2.0D); this.rotationSpeed = (float)(rot * 0.017453292519943D); } public void func_187114_a(int max) { this.field_70547_e = max; } public void setParticles(int startParticle, int numParticles, int particleInc) { this.numParticles = numParticles; this.particleInc = particleInc; this.startParticle = startParticle; func_70536_a(startParticle); } public void setParticle(int startParticle) { this.numParticles = 1; this.particleInc = 1; this.startParticle = startParticle; func_70536_a(startParticle); } public void setScale(float... scale) { this.field_70544_f = scale[0]; this.scaleKeys = scale; } public void setAlphaF(float... a1) { super.func_82338_g(a1[0]); this.alphaKeys = a1; } public void func_82338_g(float a1) { super.func_82338_g(a1); this.alphaKeys = new float[1]; this.alphaKeys[0] = a1; } public void setSlowDown(double slowDown) { this.slowDown = slowDown; } public void setRandomMovementScale(float x, float y, float z) { this.randomX = x; this.randomY = y; this.randomZ = z; } public void setFinalFrames(int... frames) { this.finalFrames = frames; }
/*     */   public void setAngles(float yaw, float pitch) { this.angleYaw = yaw; this.anglePitch = pitch; this.angled = true; }
/*     */   public void setGravity(float g) { this.field_70545_g = g; }
/*     */   public void func_70536_a(int index) { if (index < 0) index = 0;  this.field_94054_b = index % this.gridSize; this.field_94055_c = index / this.gridSize; }
/* 398 */   public void setNoClip(boolean clip) { this.field_190017_n = clip; } }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXGeneric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */