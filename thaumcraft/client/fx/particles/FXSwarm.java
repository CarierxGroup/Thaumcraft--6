/*     */ package thaumcraft.client.fx.particles;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.particle.Particle;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class FXSwarm
/*     */   extends Particle
/*     */ {
/*     */   private Entity target;
/*  19 */   private float turnSpeed = 10.0F;
/*  20 */   private float speed = 0.2F;
/*     */   int deathtimer;
/*     */   
/*     */   public FXSwarm(World par1World, double x, double y, double z, Entity target, float r, float g, float b) {
/*  24 */     super(par1World, x, y, z, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     this.deathtimer = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     this.particle = 40;
/*     */     this.field_70552_h = r;
/*     */     this.field_70553_i = g;
/*     */     this.field_70551_j = b;
/*     */     this.field_70544_f = this.field_187136_p.nextFloat() * 0.5F + 1.0F;
/*     */     this.target = target;
/*     */     float f3 = 0.2F;
/*     */     this.field_187129_i = ((this.field_187136_p.nextFloat() - this.field_187136_p.nextFloat()) * f3);
/*     */     this.field_187130_j = ((this.field_187136_p.nextFloat() - this.field_187136_p.nextFloat()) * f3);
/*     */     this.field_187131_k = ((this.field_187136_p.nextFloat() - this.field_187136_p.nextFloat()) * f3);
/*     */     this.field_70545_g = 0.1F;
/*     */   }
/*     */   
/*     */   public FXSwarm(World par1World, double x, double y, double z, Entity target, float r, float g, float b, float sp, float ts, float pg) {
/*     */     this(par1World, x, y, z, target, r, g, b);
/*     */     this.speed = sp;
/*     */     this.turnSpeed = ts;
/*     */     this.field_70545_g = pg;
/*     */   }
/*     */   
/*     */   public void func_180434_a(BufferBuilder wr, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*     */     float bob = MathHelper.func_76126_a(this.field_70546_d / 3.0F) * 0.25F + 1.0F;
/*     */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
/*     */     int part = 7 + this.field_70546_d % 8;
/*     */     float var8 = part / 64.0F;
/*     */     float var9 = var8 + 0.015625F;
/*     */     float var10 = 0.0625F;
/*     */     float var11 = var10 + 0.015625F;
/*     */     float var12 = 0.1F * this.field_70544_f * bob;
/*     */     float var13 = (float)(this.field_187123_c + (this.field_187126_f - this.field_187123_c) * f - field_70556_an);
/*     */     float var14 = (float)(this.field_187124_d + (this.field_187127_g - this.field_187124_d) * f - field_70554_ao);
/*     */     float var15 = (float)(this.field_187125_e + (this.field_187128_h - this.field_187125_e) * f - field_70555_ap);
/*     */     float var16 = 1.0F;
/*     */     float trans = (50.0F - this.deathtimer) / 50.0F;
/*     */     int i = 240;
/*     */     int j = i >> 16 & 0xFFFF;
/*     */     int k = i & 0xFFFF;
/*     */     float dd = 1.0F;
/*     */     if (this.target instanceof EntityLivingBase && ((EntityLivingBase)this.target).field_70737_aN > 0)
/*     */       dd = 2.0F; 
/*     */     wr.func_181662_b((var13 - f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 - f3 * var12 - f5 * var12)).func_187315_a(var9, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16 / dd, this.field_70551_j * var16 / dd, trans).func_187314_a(j, k).func_181675_d();
/*     */     wr.func_181662_b((var13 - f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 - f3 * var12 + f5 * var12)).func_187315_a(var9, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16 / dd, this.field_70551_j * var16 / dd, trans).func_187314_a(j, k).func_181675_d();
/*     */     wr.func_181662_b((var13 + f1 * var12 + f4 * var12), (var14 + f2 * var12), (var15 + f3 * var12 + f5 * var12)).func_187315_a(var8, var10).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16 / dd, this.field_70551_j * var16 / dd, trans).func_187314_a(j, k).func_181675_d();
/*     */     wr.func_181662_b((var13 + f1 * var12 - f4 * var12), (var14 - f2 * var12), (var15 + f3 * var12 - f5 * var12)).func_187315_a(var8, var11).func_181666_a(this.field_70552_h * var16, this.field_70553_i * var16 / dd, this.field_70551_j * var16 / dd, trans).func_187314_a(j, k).func_181675_d();
/*     */   }
/*     */   
/*     */   public int func_70537_b() { return 1; }
/*     */   
/*     */   public void func_189213_a() {
/*     */     this.field_187123_c = this.field_187126_f;
/*     */     this.field_187124_d = this.field_187127_g;
/*     */     this.field_187125_e = this.field_187128_h;
/*     */     this.field_70546_d++;
/*     */     if (this.target == null || this.target.field_70128_L || (this.target instanceof EntityLivingBase && ((EntityLivingBase)this.target).field_70725_aQ > 0)) {
/*     */       this.deathtimer++;
/*     */       this.field_187129_i *= 0.9D;
/*     */       this.field_187131_k *= 0.9D;
/*     */       this.field_187130_j -= (this.field_70545_g / 2.0F);
/*     */       if (this.deathtimer > 50)
/*     */         func_187112_i(); 
/*     */     } else {
/*     */       this.field_187130_j += this.field_70545_g;
/*     */     } 
/*     */     func_187110_a(this.field_187129_i, this.field_187130_j, this.field_187131_k);
/*     */     this.field_187129_i *= 0.985D;
/*     */     this.field_187130_j *= 0.985D;
/*     */     this.field_187131_k *= 0.985D;
/*     */     if (this.target != null && !this.target.field_70128_L && (!(this.target instanceof EntityLivingBase) || ((EntityLivingBase)this.target).field_70725_aQ <= 0)) {
/*     */       boolean hurt = false;
/*     */       if (this.target instanceof EntityLivingBase)
/*     */         hurt = (((EntityLivingBase)this.target).field_70737_aN > 0); 
/*     */       Vec3d v1 = new Vec3d(this.field_187126_f, this.field_187127_g, this.field_187128_h);
/*     */       if (v1.func_186679_c(this.target.field_70165_t, this.target.field_70163_u, this.target.field_70161_v) > this.target.field_70130_N && !hurt) {
/*     */         faceEntity(this.target, this.turnSpeed / 2.0F + this.field_187136_p.nextInt((int)(this.turnSpeed / 2.0F)), this.turnSpeed / 2.0F + this.field_187136_p.nextInt((int)(this.turnSpeed / 2.0F)));
/*     */       } else {
/*     */         faceEntity(this.target, -(this.turnSpeed / 2.0F + this.field_187136_p.nextInt((int)(this.turnSpeed / 2.0F))), -(this.turnSpeed / 2.0F + this.field_187136_p.nextInt((int)(this.turnSpeed / 2.0F))));
/*     */       } 
/*     */       this.field_187129_i = (-MathHelper.func_76126_a(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.rotationPitch / 180.0F * 3.1415927F));
/*     */       this.field_187131_k = (MathHelper.func_76134_b(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.rotationPitch / 180.0F * 3.1415927F));
/*     */       this.field_187130_j = -MathHelper.func_76126_a(this.rotationPitch / 180.0F * 3.1415927F);
/*     */       setHeading(this.field_187129_i, this.field_187130_j, this.field_187131_k, this.speed, 15.0F);
/*     */     } 
/*     */     if (buzzcount.size() < 3 && this.field_187136_p.nextInt(50) == 0 && this.field_187122_b.func_184137_a(this.field_187126_f, this.field_187127_g, this.field_187128_h, 8.0D, false) != null) {
/*     */       this.field_187122_b.func_184134_a(this.field_187126_f, this.field_187127_g, this.field_187128_h, SoundsTC.fly, SoundCategory.HOSTILE, 0.03F, 0.5F + this.field_187136_p.nextFloat() * 0.4F, false);
/*     */       buzzcount.add(Long.valueOf(System.nanoTime() + 1500000L));
/*     */     } 
/*     */     if (buzzcount.size() >= 3 && ((Long)buzzcount.get(0)).longValue() < System.nanoTime())
/*     */       buzzcount.remove(0); 
/*     */   }
/*     */   
/*     */   private static ArrayList<Long> buzzcount = new ArrayList();
/*     */   float rotationPitch;
/*     */   float rotationYaw;
/*     */   public int particle;
/*     */   
/*     */   public void faceEntity(Entity par1Entity, float par2, float par3) {
/*     */     double d0 = par1Entity.field_70165_t - this.field_187126_f;
/*     */     double d1 = par1Entity.field_70161_v - this.field_187128_h;
/*     */     double d2 = ((par1Entity.func_174813_aQ()).field_72338_b + (par1Entity.func_174813_aQ()).field_72337_e) / 2.0D - ((func_187116_l()).field_72338_b + (func_187116_l()).field_72337_e) / 2.0D;
/*     */     double d3 = MathHelper.func_76133_a(d0 * d0 + d1 * d1);
/*     */     float f2 = (float)(Math.atan2(d1, d0) * 180.0D / Math.PI) - 90.0F;
/*     */     float f3 = (float)-(Math.atan2(d2, d3) * 180.0D / Math.PI);
/*     */     this.rotationPitch = updateRotation(this.rotationPitch, f3, par3);
/*     */     this.rotationYaw = updateRotation(this.rotationYaw, f2, par2);
/*     */   }
/*     */   
/*     */   private float updateRotation(float par1, float par2, float par3) {
/*     */     float f3 = MathHelper.func_76142_g(par2 - par1);
/*     */     if (f3 > par3)
/*     */       f3 = par3; 
/*     */     if (f3 < -par3)
/*     */       f3 = -par3; 
/*     */     return par1 + f3;
/*     */   }
/*     */   
/*     */   public void setHeading(double par1, double par3, double par5, float par7, float par8) {
/*     */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/*     */     par1 /= f2;
/*     */     par3 /= f2;
/*     */     par5 /= f2;
/*     */     par1 += this.field_187136_p.nextGaussian() * (this.field_187136_p.nextBoolean() ? -1 : true) * 0.007499999832361937D * par8;
/*     */     par3 += this.field_187136_p.nextGaussian() * (this.field_187136_p.nextBoolean() ? -1 : true) * 0.007499999832361937D * par8;
/*     */     par5 += this.field_187136_p.nextGaussian() * (this.field_187136_p.nextBoolean() ? -1 : true) * 0.007499999832361937D * par8;
/*     */     par1 *= par7;
/*     */     par3 *= par7;
/*     */     par5 *= par7;
/*     */     this.field_187129_i = par1;
/*     */     this.field_187130_j = par3;
/*     */     this.field_187131_k = par5;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\fx\particles\FXSwarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */