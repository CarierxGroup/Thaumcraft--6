/*     */ package thaumcraft.client.fx.beams;
/*     */ 
/*     */ import com.sasmaster.glelwjgl.java.CoreGLE;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FXBolt
/*     */   extends Particle
/*     */ {
/*  26 */   float field_187134_n = 0.0F;
/*     */   
/*     */   ArrayList<Vec3d> points;
/*     */   
/*     */   ArrayList<Float> pointsWidth;
/*     */   
/*     */   public FXBolt(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue, float width) {
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
/*  85 */     this.points = new ArrayList();
/*  86 */     this.pointsWidth = new ArrayList();
/*  87 */     this.dr = 0.0F;
/*  88 */     this.seed = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     this.targetEntity = null;
/* 144 */     this.tX = 0.0D;
/* 145 */     this.tY = 0.0D;
/* 146 */     this.tZ = 0.0D;
/*     */     
/* 148 */     this.beam = new ResourceLocation("thaumcraft", "textures/misc/essentia.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     this.length = 1.0F;
/*     */     
/* 212 */     this.gle = new CoreGLE();
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
/*     */     this.field_187134_n = width;
/*     */     this.field_70547_e = 3;
/*     */     Vec3d vs = new Vec3d(0.0D, 0.0D, 0.0D);
/*     */     Vec3d ve = new Vec3d(this.tX, this.tY, this.tZ);
/*     */     this.length = (float)(ve.func_72433_c() * Math.PI);
/*     */     int steps = (int)this.length;
/*     */     this.points.add(vs);
/*     */     this.pointsWidth.add(Float.valueOf(width));
/*     */     this.dr = (float)(this.field_187136_p.nextInt(50) * Math.PI);
/*     */     float ampl = 0.1F;
/*     */     for (int a = 1; a < steps - 1; a++) {
/*     */       float dist = a * this.length / steps + this.dr;
/*     */       double dx = this.tX / steps * a + (MathHelper.func_76126_a(dist / 4.0F) * ampl);
/*     */       double dy = this.tY / steps * a + (MathHelper.func_76126_a(dist / 3.0F) * ampl);
/*     */       double dz = this.tZ / steps * a + (MathHelper.func_76126_a(dist / 2.0F) * ampl);
/*     */       dx += ((this.field_187136_p.nextFloat() - this.field_187136_p.nextFloat()) * 0.1F);
/*     */       dy += ((this.field_187136_p.nextFloat() - this.field_187136_p.nextFloat()) * 0.1F);
/*     */       dz += ((this.field_187136_p.nextFloat() - this.field_187136_p.nextFloat()) * 0.1F);
/*     */       Vec3d vp = new Vec3d(dx, dy, dz);
/*     */       this.points.add(vp);
/*     */       this.pointsWidth.add(Float.valueOf(width));
/*     */     } 
/*     */     this.pointsWidth.add(Float.valueOf(width));
/*     */     this.points.add(ve);
/*     */     this.seed = this.field_187136_p.nextInt(1000);
/*     */   }
/*     */   
/*     */   float dr;
/*     */   long seed;
/*     */   private Entity targetEntity;
/*     */   private double tX;
/*     */   private double tY;
/*     */   private double tZ;
/*     */   ResourceLocation beam;
/*     */   public float length;
/*     */   CoreGLE gle;
/*     */   
/*     */   public void func_189213_a() {
/*     */     this.field_187123_c = this.field_187126_f;
/*     */     this.field_187124_d = this.field_187127_g;
/*     */     this.field_187125_e = this.field_187128_h;
/*     */     if (this.field_70546_d++ >= this.field_70547_e)
/*     */       func_187112_i(); 
/*     */   }
/*     */   
/*     */   private void calcSteps(float f) {
/*     */     Random rr = new Random(this.seed);
/*     */     this.points.clear();
/*     */     this.pointsWidth.clear();
/*     */     Vec3d vs = new Vec3d(0.0D, 0.0D, 0.0D);
/*     */     Vec3d ve = new Vec3d(this.tX, this.tY, this.tZ);
/*     */     int steps = (int)this.length;
/*     */     this.points.add(vs);
/*     */     this.pointsWidth.add(Float.valueOf(this.field_187134_n));
/*     */     float ampl = (this.field_70546_d + f) / 10.0F;
/*     */     for (int a = 1; a < steps - 1; a++) {
/*     */       float dist = a * this.length / steps + this.dr;
/*     */       double dx = this.tX / steps * a + (MathHelper.func_76126_a(dist / 4.0F) * ampl);
/*     */       double dy = this.tY / steps * a + (MathHelper.func_76126_a(dist / 3.0F) * ampl);
/*     */       double dz = this.tZ / steps * a + (MathHelper.func_76126_a(dist / 2.0F) * ampl);
/*     */       dx += ((rr.nextFloat() - rr.nextFloat()) * 0.1F);
/*     */       dy += ((rr.nextFloat() - rr.nextFloat()) * 0.1F);
/*     */       dz += ((rr.nextFloat() - rr.nextFloat()) * 0.1F);
/*     */       Vec3d vp = new Vec3d(dx, dy, dz);
/*     */       this.points.add(vp);
/*     */       this.pointsWidth.add(Float.valueOf(((rr.nextInt(4) == 0) ? (1.0F - this.field_70546_d * 0.25F) : 1.0F) * this.field_187134_n));
/*     */     } 
/*     */     this.pointsWidth.add(Float.valueOf(this.field_187134_n));
/*     */     this.points.add(ve);
/*     */   }
/*     */   
/*     */   public void setRGB(float r, float g, float b) {
/*     */     this.field_70552_h = r;
/*     */     this.field_70553_i = g;
/*     */     this.field_70551_j = b;
/*     */   }
/*     */   
/*     */   public void func_180434_a(BufferBuilder wr, Entity entity, float f, float cosyaw, float cospitch, float sinyaw, float cossinpitch, float f5) {
/*     */     Tessellator.func_178181_a().func_78381_a();
/*     */     GL11.glPushMatrix();
/*     */     this;
/*     */     double ePX = this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an;
/*     */     this;
/*     */     double ePY = this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao;
/*     */     this;
/*     */     double ePZ = this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap;
/*     */     GL11.glTranslated(ePX, ePY, ePZ);
/*     */     (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.beam);
/*     */     GL11.glDepthMask(false);
/*     */     GL11.glEnable(3042);
/*     */     GL11.glBlendFunc(770, 1);
/*     */     GL11.glDisable(2884);
/*     */     calcSteps(f);
/*     */     float alpha = MathHelper.func_76131_a(1.0F - this.field_70546_d / this.field_70547_e, 0.1F, 1.0F);
/*     */     if (this.points != null && this.points.size() > 2) {
/*     */       double[][] pp = new double[this.points.size()][3];
/*     */       float[][] colours = new float[this.points.size()][4];
/*     */       double[] radii = new double[this.points.size()];
/*     */       for (int a = 0; a < this.points.size(); a++) {
/*     */         pp[a][0] = ((Vec3d)this.points.get(a)).field_72450_a;
/*     */         pp[a][1] = ((Vec3d)this.points.get(a)).field_72448_b;
/*     */         pp[a][2] = ((Vec3d)this.points.get(a)).field_72449_c;
/*     */         colours[a][0] = this.field_70552_h;
/*     */         colours[a][1] = this.field_70553_i;
/*     */         colours[a][2] = this.field_70551_j;
/*     */         colours[a][3] = alpha;
/*     */         radii[a] = (((Float)this.pointsWidth.get(a)).floatValue() / 10.0F);
/*     */       } 
/*     */       this.gle.set_POLYCYL_TESS(5);
/*     */       this.gle.gleSetJoinStyle(1042);
/*     */       this.gle.glePolyCone(pp.length, pp, colours, radii, 1.0F, 0.0F);
/*     */       for (int a = 0; a < this.points.size(); a++)
/*     */         radii[a] = radii[a] / 3.0D; 
/*     */       this.gle.glePolyCone(pp.length, pp, colours, radii, 1.0F, 0.0F);
/*     */     } 
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


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\beams\FXBolt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */