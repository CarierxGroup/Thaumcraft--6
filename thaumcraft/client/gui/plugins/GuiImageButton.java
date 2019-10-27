/*     */ package thaumcraft.client.gui.plugins;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class GuiImageButton extends GuiButton {
/*     */   GuiScreen screen;
/*     */   ResourceLocation loc;
/*     */   int lx;
/*     */   int ly;
/*     */   int ww;
/*     */   int hh;
/*     */   public String description;
/*     */   public int color;
/*     */   public boolean active;
/*     */   
/*  21 */   public GuiImageButton(GuiScreen screen, int buttonId, int x, int y, int width, int height, String buttonText, String description, ResourceLocation loc, int lx, int ly, int ww, int hh) { super(buttonId, x, y, width, height, buttonText);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     this.active = true; this.description = description; this.screen = screen; this.color = 16777215; this.loc = loc; this.lx = lx; this.ly = ly; this.ww = ww; this.hh = hh; } public GuiImageButton(GuiScreen screen, int buttonId, int x, int y, int width, int height, String buttonText, String description, ResourceLocation loc, int lx, int ly, int ww, int hh, int color) { super(buttonId, x, y, width, height, buttonText); this.active = true; this.description = description; this.screen = screen; this.color = color;
/*     */     this.loc = loc;
/*     */     this.lx = lx;
/*     */     this.ly = ly;
/*     */     this.ww = ww;
/*  55 */     this.hh = hh; } public void func_191745_a(Minecraft mc, int xx, int yy, float pt) { if (this.field_146125_m) {
/*     */       
/*  57 */       FontRenderer fontrenderer = mc.field_71466_p;
/*     */       
/*  59 */       this.field_146123_n = (xx >= this.field_146128_h - this.field_146120_f / 2 && yy >= this.field_146129_i - this.field_146121_g / 2 && xx < this.field_146128_h - this.field_146120_f / 2 + this.field_146120_f && yy < this.field_146129_i - this.field_146121_g / 2 + this.field_146121_g);
/*  60 */       int k = func_146114_a(this.field_146123_n);
/*     */       
/*  62 */       GlStateManager.func_179147_l();
/*  63 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/*  64 */       GlStateManager.func_179112_b(770, 771);
/*     */       
/*  66 */       Color c = new Color(this.color);
/*  67 */       float cc = 0.9F;
/*  68 */       float ac = 1.0F;
/*     */       
/*  70 */       if (k == 2) {
/*  71 */         ac = 1.0F;
/*  72 */         cc = 1.0F;
/*     */       } 
/*     */       
/*  75 */       if (!this.active) {
/*  76 */         cc = 0.5F;
/*  77 */         ac = 0.9F;
/*     */       } 
/*     */       
/*  80 */       GlStateManager.func_179131_c(cc * c.getRed() / 255.0F, cc * c.getGreen() / 255.0F, cc * c.getBlue() / 255.0F, ac);
/*     */       
/*  82 */       mc.func_110434_K().func_110577_a(this.loc);
/*  83 */       func_73729_b(this.field_146128_h - this.ww / 2, this.field_146129_i - this.hh / 2, this.lx, this.ly, this.ww, this.hh);
/*     */       
/*  85 */       GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*  87 */       if (this.field_146126_j != null) {
/*  88 */         int j = 16777215;
/*  89 */         if (!this.field_146124_l) {
/*     */           
/*  91 */           j = 10526880;
/*     */         }
/*  93 */         else if (this.field_146123_n) {
/*     */           
/*  95 */           j = 16777120;
/*     */         } 
/*  97 */         GL11.glPushMatrix();
/*  98 */         GL11.glTranslated(this.field_146128_h, this.field_146129_i, 0.0D);
/*  99 */         GL11.glScaled(0.5D, 0.5D, 0.0D);
/* 100 */         func_73732_a(fontrenderer, (new TextComponentTranslation(this.field_146126_j, new Object[0])).func_150254_d(), 0, -4, j);
/* 101 */         GL11.glPopMatrix();
/*     */       } 
/*     */       
/* 104 */       func_146119_b(mc, xx, yy);
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146111_b(int xx, int yy) {
/* 111 */     FontRenderer fontrenderer = (Minecraft.func_71410_x()).field_71466_p;
/* 112 */     this.field_73735_i += 90.0F;
/* 113 */     ArrayList<String> text = new ArrayList<String>();
/* 114 */     if (this.field_146126_j != null) text.add(this.field_146126_j); 
/* 115 */     int m = 8;
/* 116 */     if (this.description != null) {
/* 117 */       m = 0;
/* 118 */       text.add("§o§9" + this.description);
/*     */     } 
/* 120 */     UtilsFX.drawCustomTooltip(this.screen, fontrenderer, text, xx + 4, yy + m, -99);
/* 121 */     this.field_73735_i -= 90.0F;
/* 122 */     RenderHelper.func_74518_a();
/* 123 */     GlStateManager.func_179140_f();
/* 124 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 129 */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) { return (this.active && this.field_146124_l && this.field_146125_m && mouseX >= this.field_146128_h - this.field_146120_f / 2 && mouseY >= this.field_146129_i - this.field_146121_g / 2 && mouseX < this.field_146128_h - this.field_146120_f / 2 + this.field_146120_f && mouseY < this.field_146129_i - this.field_146121_g / 2 + this.field_146121_g); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\plugins\GuiImageButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */