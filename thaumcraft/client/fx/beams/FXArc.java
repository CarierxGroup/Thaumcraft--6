/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.particle.ParticleManager;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.client.fx.ParticleEngine;
/*     */ import thaumcraft.client.fx.particles.FXGeneric;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FXArc
/*     */   extends Particle
/*     */ {
/*  25 */   public int particle = 16;
/*     */   
/*     */   ArrayList<Vec3d> points;
/*     */   private Entity targetEntity;
/*     */   private double tX;
/*     */   private double tY;
/*     */   
/*     */   public FXArc(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue, double hg) {
/*  33 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
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
/* 106 */     this.points = new ArrayList();
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
/* 132 */     this.targetEntity = null;
/* 133 */     this.tX = 0.0D;
/* 134 */     this.tY = 0.0D;
/* 135 */     this.tZ = 0.0D;
/*     */     
/* 137 */     this.beam = new ResourceLocation("thaumcraft", "textures/misc/beamh.png");
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
/* 208 */     this.blendmode = 1;
/* 209 */     this.length = 1.0F;
/*     */     this.field_70552_h = red;
/*     */     this.field_70553_i = green;
/*     */     this.field_70551_j = blue;
/*     */     func_187115_a(0.02F, 0.02F);
/*     */     this.field_187129_i = 0.0D;
/*     */     this.field_187130_j = 0.0D;
/*     */     this.field_187131_k = 0.0D;
/*     */     this.tX = tx - x;
/*     */     this.tY = ty - y;
/*     */     this.tZ = tz - z;
/*     */     this.field_70547_e = 3;
/*     */     double xx = 0.0D;
/*     */     double yy = 0.0D;
/*     */     double zz = 0.0D;
/*     */     double gravity = 0.115D;
/*     */     double noise = 0.25D;
/*     */     Vec3d vs = new Vec3d(xx, yy, zz);
/*     */     Vec3d ve = new Vec3d(this.tX, this.tY, this.tZ);
/*     */     Vec3d vc = new Vec3d(xx, yy, zz);
/*     */     this.length = (float)ve.func_72433_c();
/*     */     Vec3d vv = Utils.calculateVelocity(vs, ve, hg, gravity);
/*     */     double l = Utils.distanceSquared3d(new Vec3d(0.0D, 0.0D, 0.0D), vv);
/*     */     this.points.add(vs);
/*     */     int c = 0;
/*     */     while (Utils.distanceSquared3d(ve, vc) > l && c < 50) {
/*     */       Vec3d vt = vc.func_72441_c(vv.field_72450_a, vv.field_72448_b, vv.field_72449_c);
/*     */       vc = new Vec3d(vt.field_72450_a, vt.field_72448_b, vt.field_72449_c);
/*     */       vt = vt.func_72441_c((this.field_187136_p.nextDouble() - this.field_187136_p.nextDouble()) * noise, (this.field_187136_p.nextDouble() - this.field_187136_p.nextDouble()) * noise, (this.field_187136_p.nextDouble() - this.field_187136_p.nextDouble()) * noise);
/*     */       this.points.add(vt);
/*     */       FXGeneric fb = new FXGeneric(par1World, x + vt.field_72450_a, y + vt.field_72448_b, z + vt.field_72449_c, 0.0D, 0.0D, 0.0D);
/*     */       int age = 30 + this.field_187136_p.nextInt(20);
/*     */       fb.func_187114_a(age);
/*     */       fb.setRBGColorF(MathHelper.func_76131_a(red * 3.0F, 0.0F, 1.0F), MathHelper.func_76131_a(green * 3.0F, 0.0F, 1.0F), MathHelper.func_76131_a(blue * 3.0F, 0.0F, 1.0F), this.field_187136_p.nextFloat(), this.field_187136_p.nextFloat(), this.field_187136_p.nextFloat());
/*     */       float[] alphas = new float[6 + this.field_187136_p.nextInt(age / 3)];
/*     */       for (int a = 1; a < alphas.length - 1; ) {
/*     */         alphas[a] = this.field_187136_p.nextFloat();
/*     */         a++;
/*     */       } 
/*     */       alphas[0] = 1.0F;
/*     */       fb.setAlphaF(alphas);
/*     */       boolean sp = (this.field_187136_p.nextFloat() < 0.2D);
/*     */       fb.setParticles(sp ? 320 : 512, 16, 1);
/*     */       fb.setLoop(true);
/*     */       fb.setGravity(sp ? 0.0F : 0.125F);
/*     */       fb.setScale(new float[] { 0.5F, 0.125F });
/*     */       fb.setLayer(0);
/*     */       fb.setSlowDown(0.995D);
/*     */       fb.setRandomMovementScale(0.0025F, 0.001F, 0.0025F);
/*     */       ParticleEngine.addEffectWithDelay(par1World, fb, 2 + this.field_187136_p.nextInt(3));
/*     */       vv = vv.func_178786_a(0.0D, gravity / 1.9D, 0.0D);
/*     */       c++;
/*     */     } 
/*     */     this.points.add(ve);
/*     */   }
/*     */   
/*     */   private double tZ;
/*     */   ResourceLocation beam;
/*     */   public int blendmode;
/*     */   public float length;
/*     */   
/*     */   public void func_189213_a() {
/*     */     this.field_187123_c = this.field_187126_f;
/*     */     this.field_187124_d = this.field_187127_g;
/*     */     this.field_187125_e = this.field_187128_h;
/*     */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */       func_187112_i(); 
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/*     */     this.field_70552_h = r;
/*     */     this.field_70553_i = g;
/*     */     this.field_70551_j = b;
/*     */   }
/*     */   
/*     */   public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*     */     Tessellator.func_178181_a().func_78381_a();
/*     */     GL11.glPushMatrix();
/*     */     this;
/*     */     double ePX = this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an;
/*     */     this;
/*     */     double ePY = this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao;
/*     */     this;
/*     */     double ePZ = this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap;
/*     */     GL11.glTranslated(ePX, ePY, ePZ);
/*     */     float size = 0.125F;
/*     */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam);
/*     */     GL11.glDepthMask(false);
/*     */     GL11.glEnable(3042);
/*     */     GL11.glBlendFunc(770, 1);
/*     */     GL11.glDisable(2884);
/*     */     int i = 220;
/*     */     int j = i >> 16 & 0xFFFF;
/*     */     int k = i & 0xFFFF;
/*     */     float alpha = 1.0F - (this.field_70546_d + f) / this.field_70547_e;
/*     */     wr.func_181668_a(5, DefaultVertexFormats.field_181711_k);
/*     */     float f9 = 0.0F;
/*     */     float f10 = 1.0F;
/*     */     for (int c = 0; c < this.points.size(); c++) {
/*     */       Vec3d v = (Vec3d)this.points.get(c);
/*     */       float f13 = c / this.length;
/*     */       double dx = v.field_72450_a;
/*     */       double dy = v.field_72448_b;
/*     */       double dz = v.field_72449_c;
/*     */       wr.func_181662_b(dx, dy - size, dz).func_187315_a(f13, f10).func_187314_a(j, k).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, alpha).func_181675_d();
/*     */       wr.func_181662_b(dx, dy + size, dz).func_187315_a(f13, f9).func_187314_a(j, k).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, alpha).func_181675_d();
/*     */     } 
/*     */     Tessellator.func_178181_a().func_78381_a();
/*     */     wr.func_181668_a(5, DefaultVertexFormats.field_181711_k);
/*     */     for (int c = 0; c < this.points.size(); c++) {
/*     */       Vec3d v = (Vec3d)this.points.get(c);
/*     */       float f13 = c / this.length;
/*     */       double dx = v.field_72450_a;
/*     */       double dy = v.field_72448_b;
/*     */       double dz = v.field_72449_c;
/*     */       wr.func_181662_b(dx - size, dy, dz - size).func_187315_a(f13, f10).func_187314_a(j, k).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, alpha).func_181675_d();
/*     */       wr.func_181662_b(dx + size, dy, dz + size).func_187315_a(f13, f9).func_187314_a(j, k).func_181666_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, alpha).func_181675_d();
/*     */     } 
/*     */     Tessellator.func_178181_a().func_78381_a();
/*     */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     GL11.glEnable(2884);
/*     */     GL11.glBlendFunc(770, 771);
/*     */     GL11.glDisable(3042);
/*     */     GL11.glDepthMask(true);
/*     */     GL11.glPopMatrix();
/*     */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(ParticleManager.field_110737_b);
/*     */     wr.func_181668_a(7, DefaultVertexFormats.field_181704_d);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\beams\FXArc.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */