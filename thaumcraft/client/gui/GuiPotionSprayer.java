/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.container.ContainerPotionSprayer;
/*    */ import thaumcraft.common.tiles.devices.TilePotionSprayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiPotionSprayer
/*    */   extends GuiContainer
/*    */ {
/*    */   private TilePotionSprayer inventory;
/* 23 */   private ContainerPotionSprayer container = null;
/* 24 */   private EntityPlayer player = null;
/*    */   ResourceLocation tex;
/*    */   int startAspect;
/*    */   
/* 28 */   public GuiPotionSprayer(InventoryPlayer par1InventoryPlayer, TilePotionSprayer tilePotionSprayer) { super(new ContainerPotionSprayer(par1InventoryPlayer, tilePotionSprayer));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_potion_sprayer.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 72 */     this.startAspect = 0; this.field_146999_f = 192; this.field_147000_g = 233;
/*    */     this.inventory = tilePotionSprayer;
/*    */     this.container = (ContainerPotionSprayer)this.field_147002_h;
/* 75 */     this.player = par1InventoryPlayer.field_70458_d; } private void drawAspects(int k, int l) { int pos = 0;
/* 76 */     for (Aspect aspect : this.inventory.recipe.getAspectsSortedByName()) {
/* 77 */       GL11.glPushMatrix();
/* 78 */       GL11.glColor4f(0.2F, 0.2F, 0.2F, 1.0F);
/* 79 */       func_73729_b(k + 96 + 22 * pos % 2, l + 46 + 16 * pos / 2 - 14, 192, 56, 2, 14);
/* 80 */       int i1 = (int)(this.inventory.recipeProgress.getAmount(aspect) / this.inventory.recipe.getAmount(aspect) * 14.0F);
/* 81 */       Color c = new Color(aspect.getColor());
/* 82 */       GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/* 83 */       func_73729_b(k + 96 + 22 * pos % 2, l + 46 + 16 * pos / 2 - i1, 192, 56, 2, i1);
/* 84 */       GL11.glPopMatrix();
/* 85 */       pos++;
/*    */     } 
/* 87 */     pos = 0;
/* 88 */     for (Aspect aspect : this.inventory.recipe.getAspectsSortedByName()) {
/* 89 */       UtilsFX.drawTag(k + 79 + 22 * pos % 2, l + 31 + 16 * pos / 2, aspect, this.inventory.recipe.getAmount(aspect), 0, this.field_73735_i);
/* 90 */       pos++;
/*    */     } 
/* 92 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); }
/*    */ 
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*    */     func_146276_q_();
/*    */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*    */     func_191948_b(mouseX, mouseY);
/*    */   }
/*    */   
/*    */   protected void func_146976_a(float par1, int mx, int my) {
/*    */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*    */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*    */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*    */     GL11.glEnable(3042);
/*    */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */     if (this.inventory.charges > 0) {
/*    */       Color c = new Color(this.inventory.color);
/*    */       GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 1.0F);
/*    */       int scroll = this.player.field_70173_aa % 256;
/*    */       func_73729_b(k + 128, l + 36 + (8 - this.inventory.charges) * 9, 232, scroll, 8, this.inventory.charges * 9);
/*    */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     } 
/*    */     drawAspects(k, l);
/*    */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*    */     func_73729_b(k + 125, l + 28, 205, 28, 14, 88);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiPotionSprayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */