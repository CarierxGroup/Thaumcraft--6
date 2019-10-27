/*     */ package thaumcraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.api.research.theorycraft.ITheorycraftAid;
/*     */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*     */ import thaumcraft.api.research.theorycraft.TheorycraftManager;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.client.gui.plugins.GuiImageButton;
/*     */ import thaumcraft.client.lib.UtilsFX;
/*     */ import thaumcraft.common.container.ContainerResearchTable;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketStartTheoryToServer;
/*     */ import thaumcraft.common.tiles.crafting.TileResearchTable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiResearchTable
/*     */   extends GuiContainer
/*     */ {
/*     */   private float xSize_lo;
/*     */   private float ySize_lo;
/*     */   private TileResearchTable table;
/*     */   private FontRenderer galFontRenderer;
/*     */   private String username;
/*     */   EntityPlayer player;
/*  62 */   ResourceLocation txBackground = new ResourceLocation("thaumcraft", "textures/gui/gui_research_table.png");
/*  63 */   ResourceLocation txBase = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*  64 */   ResourceLocation txPaper = new ResourceLocation("thaumcraft", "textures/gui/paper.png");
/*  65 */   ResourceLocation txPaperGilded = new ResourceLocation("thaumcraft", "textures/gui/papergilded.png");
/*  66 */   ResourceLocation txQuestion = new ResourceLocation("thaumcraft", "textures/aspects/_unknown.png");
/*     */   ResearchTableData.CardChoice lastDraw;
/*     */   float[] cardHover; float[] cardZoomOut; float[] cardZoomIn; boolean[] cardActive; boolean cardSelected; public HashMap<String, Integer> tempCatTotals; long nexCatCheck; long nextCheck; int dummyInspirationStart; Set<String> currentAids; Set<String> selectedAids; GuiImageButton buttonCreate; GuiImageButton buttonComplete; GuiImageButton buttonScrap; public ArrayList<ResearchTableData.CardChoice> cardChoices; private void syncFromTableChoices() { this.cardChoices.clear(); for (ResearchTableData.CardChoice cc : this.table.data.cardChoices) this.cardChoices.add(cc);  } protected void func_146979_b(int mx, int my) {} public void func_73863_a(int mx, int my, float par3) { func_146276_q_(); super.func_73863_a(mx, my, par3); this.xSize_lo = mx; this.ySize_lo = my; int xx = this.field_147003_i; int yy = this.field_147009_r; RenderHelper.func_74518_a(); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); if (this.table.data == null) { if (!this.currentAids.isEmpty()) { int side = Math.min(this.currentAids.size(), 6); int c = 0; int r = 0; GL11.glPushMatrix(); GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.2F); this.field_146297_k.field_71446_o.func_110577_a(this.txBase); for (String key : this.currentAids) { ITheorycraftAid mutator = (ITheorycraftAid)TheorycraftManager.aids.get(key); if (mutator == null) continue;  int x = xx + 128 + 20 * c - side * 10; int y = yy + 85 + 35 * r; if (func_146978_c(x - xx, y - yy, 16, 16, mx, my) && !this.selectedAids.contains(key)) func_73729_b(x, y, 0, 96, 16, 16);  c++; if (c >= side) { r++; c = 0; }  }  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GL11.glPopMatrix(); }  } else { int sx = 128; int cw = 110; int sz = this.cardChoices.size(); int a = 0; if (!this.cardSelected)
/*     */         for (ResearchTableData.CardChoice cardChoice : this.cardChoices) { if (this.cardZoomOut[a] >= 1.0F) { float dx = (55 + sx - 55 * sz + cw * a - 65); float fx = 65.0F + dx * this.cardZoomOut[a]; float qx = 191.0F - fx; if (this.cardActive[a])
/*  70 */               fx += qx * this.cardZoomIn[a];  drawSheetOverlay(fx, 100.0D, cardChoice, mx, my); a++; }  }   int qq = 0; if (this.table.func_70301_a(false) == null || this.table.func_70301_a(0).func_190926_b() || this.table.func_70301_a(0).func_77952_i() == this.table.func_70301_a(0).func_77958_k()) { sx = Math.max(this.field_146289_q.func_78256_a(I18n.func_74838_a("tile.researchtable.noink.0")), this.field_146289_q.func_78256_a(I18n.func_74838_a("tile.researchtable.noink.1"))) / 2; UtilsFX.drawCustomTooltip(this, this.field_146289_q, Arrays.asList(new String[] { I18n.func_74838_a("tile.researchtable.noink.0"), I18n.func_74838_a("tile.researchtable.noink.1") }, ), xx - sx + 116, yy + 60 + qq, 11, true); qq += 40; }  if (this.table.func_70301_a(true) == null || this.table.func_70301_a(1).func_190926_b()) { sx = this.field_146289_q.func_78256_a(I18n.func_74838_a("tile.researchtable.nopaper.0")) / 2; UtilsFX.drawCustomTooltip(this, this.field_146289_q, Arrays.asList(new String[] { I18n.func_74838_a("tile.researchtable.nopaper.0") }, ), xx - sx + 116, yy + 60 + qq, 11, true); }  }  func_191948_b(mx, my); } public GuiResearchTable(EntityPlayer player, TileResearchTable e) { super(new ContainerResearchTable(player.field_71071_by, e));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     this.cardHover = new float[] { 0.0F, 0.0F, 0.0F };
/* 189 */     this.cardZoomOut = new float[] { 0.0F, 0.0F, 0.0F };
/* 190 */     this.cardZoomIn = new float[] { 0.0F, 0.0F, 0.0F };
/* 191 */     this.cardActive = new boolean[] { true, true, true };
/* 192 */     this.cardSelected = false;
/*     */     
/* 194 */     this.tempCatTotals = new HashMap();
/* 195 */     this.nexCatCheck = 0L;
/*     */     
/* 197 */     this.nextCheck = 0L;
/* 198 */     this.dummyInspirationStart = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 661 */     this.currentAids = new HashSet();
/* 662 */     this.selectedAids = new HashSet();
/*     */     
/* 664 */     this.buttonCreate = new GuiImageButton(this, 1, this.field_147003_i + 128, this.field_147009_r + 22, 49, 11, "button.create.theory", null, this.txBase, 37, 66, 51, 13, 8978346);
/* 665 */     this.buttonComplete = new GuiImageButton(this, 7, this.field_147003_i + 191, this.field_147009_r + 96, 49, 11, "button.complete.theory", null, this.txBase, 37, 66, 51, 13, 8978346);
/* 666 */     this.buttonScrap = new GuiImageButton(this, 9, this.field_147003_i + 128, this.field_147009_r + 168, 49, 11, "button.scrap.theory", null, this.txBase, 37, 66, 51, 13, 16720418);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 803 */     this.cardChoices = new ArrayList(); this.table = e; this.field_146999_f = 255; this.field_147000_g = 255; this.galFontRenderer = (FMLClientHandler.instance().getClient()).field_71464_q; this.username = player.func_70005_c_(); this.player = player; if (this.table.data != null) { for (String cat : this.table.data.categoryTotals.keySet()) this.tempCatTotals.put(cat, this.table.data.categoryTotals.get(cat));  syncFromTableChoices(); this.lastDraw = this.table.data.lastDraw; }  }
/*     */   protected void func_146976_a(float partialTicks, int mx, int my) { checkButtons(); int xx = this.field_147003_i; int yy = this.field_147009_r; GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GL11.glEnable(3042); GL11.glBlendFunc(770, 771); this.field_146297_k.field_71446_o.func_110577_a(this.txBackground); func_73729_b(xx, yy, 0, 0, 255, 255); this.field_146289_q.func_78276_b(" ", 0, 0, 0); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); if (this.table.data == null) { if (this.nextCheck < this.player.field_70173_aa) { this.currentAids = this.table.checkSurroundingAids(); this.dummyInspirationStart = ResearchTableData.getAvailableInspiration(this.player); this.nextCheck = (this.player.field_70173_aa + 100); }  this.field_146297_k.field_71446_o.func_110577_a(this.txBase); GL11.glPushMatrix(); GL11.glTranslated((xx + 128 - this.dummyInspirationStart * 5), (yy + 55), 0.0D); GL11.glScaled(0.5D, 0.5D, 0.0D); for (int a = 0; a < this.dummyInspirationStart; a++) func_73729_b(20 * a, 0, (this.dummyInspirationStart - this.selectedAids.size() <= a) ? 48 : 32, 96, 16, 16);  GL11.glPopMatrix(); if (!this.currentAids.isEmpty()) { int side = Math.min(this.currentAids.size(), 6); int c = 0; int r = 0; for (String key : this.currentAids) { ITheorycraftAid mutator = (ITheorycraftAid)TheorycraftManager.aids.get(key); if (mutator == null) continue;  int x = xx + 128 + 20 * c - side * 10; int y = yy + 85 + 35 * r; if (this.selectedAids.contains(key)) { this.field_146297_k.field_71446_o.func_110577_a(this.txBase); func_73729_b(x, y, 0, 96, 16, 16); }  if (mutator.getAidObject() instanceof ItemStack || mutator.getAidObject() instanceof Block) { GL11.glPushMatrix(); RenderHelper.func_74520_c(); GlStateManager.func_179140_f(); GlStateManager.func_179091_B(); GlStateManager.func_179142_g(); GlStateManager.func_179145_e(); ItemStack s = (mutator.getAidObject() instanceof ItemStack) ? (ItemStack)mutator.getAidObject() : new ItemStack((Block)mutator.getAidObject()); this.field_146296_j.func_180450_b(s, x, y); GlStateManager.func_179140_f(); GlStateManager.func_179132_a(true); GlStateManager.func_179126_j(); GL11.glPopMatrix(); }  c++; if (c >= side) { r++; c = 0; }  }  }  } else { checkCards(); this.field_146297_k.field_71446_o.func_110577_a(this.txBase); GL11.glPushMatrix(); GL11.glTranslated((xx + 15), (yy + 150), 0.0D); if (this.table.data != null) for (int a = 0; a < this.table.data.bonusDraws; a++) func_73729_b(a * 2, a, 64, 96, 16, 16);   GL11.glPopMatrix(); GL11.glPushMatrix(); GL11.glTranslated((xx + 128 - this.table.data.inspirationStart * 5), (yy + 16), 0.0D); GL11.glScaled(0.5D, 0.5D, 0.0D); for (int a = 0; a < this.table.data.inspirationStart; a++) func_73729_b(20 * a, 0, (this.table.data.inspiration <= a) ? 48 : 32, 96, 16, 16);  GL11.glPopMatrix(); int sheets = 0; if (this.table.func_70301_a(true) != null) sheets = 1 + this.table.func_70301_a(1).func_190916_E() / 4;  Random r = new Random(55L); if (sheets > 0 && !this.table.data.isComplete()) { for (int a = 0; a < sheets; a++) drawSheet((xx + 65), (yy + 100), 6.0D, r, 1.0F, 1.0F, null);  boolean highlight = false; int var7 = mx - 25 + xx; int var8 = my - 55 + yy; if (this.cardChoices.isEmpty() && var7 >= 0 && var8 >= 0 && var7 < 75 && var8 < 90) highlight = true;  GL11.glPushMatrix(); GL11.glColor4f(1.0F, 1.0F, 1.0F, highlight ? 1.0F : 0.5F); GlStateManager.func_179147_l(); this.field_146297_k.field_71446_o.func_110577_a(this.txQuestion); GL11.glTranslated((xx + 65), (yy + 100), 0.0D); GL11.glScaled(highlight ? 1.75D : 1.5D, highlight ? 1.75D : 1.5D, 0.0D); UtilsFX.drawTexturedQuadFull(-8.0F, -8.0F, 0.0D); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GL11.glPopMatrix(); }  for (Long seed : this.table.data.savedCards) { r = new Random(seed.longValue()); drawSheet((xx + 191), (yy + 100), 6.0D, r, 1.0F, 1.0F, null); }  if (this.lastDraw != null) { r = new Random(this.lastDraw.card.getSeed()); drawSheet((xx + 191), (yy + 100), 6.0D, r, 1.0F, 1.0F, this.lastDraw); }  ArrayList<String> sparkle = new ArrayList<String>(); if (this.nexCatCheck < this.player.field_70173_aa) { for (String cat : ResearchCategories.researchCategories.keySet()) { int t0 = 0; if (this.table.data.categoryTotals.containsKey(cat)) t0 = ((Integer)this.table.data.categoryTotals.get(cat)).intValue();  int t1 = 0; if (this.tempCatTotals.containsKey(cat)) t1 = ((Integer)this.tempCatTotals.get(cat)).intValue();  if (t0 == 0 && t1 == 0) { this.tempCatTotals.remove(cat); continue; }  if (t1 > t0) t1--;  if (t1 < t0) { t1++; sparkle.add(cat); }  this.tempCatTotals.put(cat, Integer.valueOf(t1)); }  this.nexCatCheck = (this.player.field_70173_aa + 1); }  HashMap<String, Integer> unsortedMap = new HashMap<String, Integer>(); String hf = null; int lf = 0; for (String cat : this.tempCatTotals.keySet()) { int cf = ((Integer)this.tempCatTotals.get(cat)).intValue(); if (cf == 0) continue;  if (cf > lf) { lf = cf; hf = cat; }  unsortedMap.put(cat, Integer.valueOf(cf)); }  if (hf != null) unsortedMap.put(hf, unsortedMap.get(hf));  Comparator<Map.Entry<String, Integer>> valueComparator = (e1, e2) -> ((Integer)e2.getValue()).compareTo((Integer)e1.getValue()); Map<String, Integer> sortedMap = (Map)unsortedMap.entrySet().stream().sorted(valueComparator).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, java.util.LinkedHashMap::new)); GL11.glEnable(3042); GL11.glBlendFunc(770, 771); int i = 0; for (String field : sortedMap.keySet()) { GL11.glPushMatrix(); GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); GL11.glTranslatef((xx + 253), (yy + 16 + i * 18 + ((i > 0) ? 4 : 0)), 0.0F); GL11.glScaled(0.0625D, 0.0625D, 0.0625D); this.field_146297_k.field_71446_o.func_110577_a((ResearchCategories.getResearchCategory(field)).icon); func_73729_b(0, 0, 0, 0, 255, 255); GL11.glPopMatrix(); GL11.glTranslatef(0.0F, 0.0F, 5.0F); String s = sortedMap.get(field) + "%"; if (i > this.table.data.penaltyStart) { int q = ((Integer)sortedMap.get(field)).intValue() / 3; s = s + " (-" + q + ")"; }  this.field_146297_k.field_71466_p.func_175063_a(s, (xx + 276), (yy + 20 + i * 18 + ((i > this.table.data.penaltyStart) ? 4 : 0)), this.table.data.categoriesBlocked.contains(field) ? 6316128 : ((i <= this.table.data.penaltyStart) ? 57536 : 16777215)); if (sparkle.contains(field)) for (int q = 0; q < 2; q++) { float rr = MathHelper.func_76136_a(this.player.func_70681_au(), 255, 255) / 255.0F; float gg = MathHelper.func_76136_a(this.player.func_70681_au(), 189, 255) / 255.0F; float bb = MathHelper.func_76136_a(this.player.func_70681_au(), 64, 255) / 255.0F; FXDispatcher.INSTANCE.drawSimpleSparkleGui(this.player.func_70681_au(), ((xx + 276) + this.player.func_70681_au().nextFloat() * this.field_146289_q.func_78256_a(s)), ((yy + 20) + this.player.func_70681_au().nextFloat() * 8.0F + (i * 18) + ((i > this.table.data.penaltyStart) ? 4 : 0)), this.player.field_70170_p.field_73012_v.nextGaussian() * 0.5D, this.player.field_70170_p.field_73012_v.nextGaussian() * 0.5D, 24.0F, rr, gg, bb, 0, 0.9F, -1.0F); }   int var7 = mx - xx + 256; int var8 = my - yy + 16 + i * 18 + ((i > this.table.data.penaltyStart) ? 4 : 0); if (var7 >= 0 && var8 >= 0 && var7 < 16 && var8 < 16) { GL11.glPushMatrix(); UtilsFX.drawCustomTooltip(this, this.field_146289_q, Arrays.asList(new String[] { ResearchCategories.getCategoryName(field) }, ), mx + 8, my + 8, 11); GL11.glPopMatrix(); RenderHelper.func_74518_a(); }  i++; }  int sx = 128; int cw = 110; int sz = this.cardChoices.size(); int a = 0; for (ResearchTableData.CardChoice cardChoice : this.cardChoices) { r = new Random(cardChoice.card.getSeed()); int var7 = mx - 5 + sx - 55 * sz + xx + cw * a; int var8 = my - 100 + yy - 60; if (this.cardZoomOut[a] >= 0.95D && !this.cardSelected) if (var7 >= 0 && var8 >= 0 && var7 < 100 && var8 < 120) { this.cardHover[a] = this.cardHover[a] + Math.max((0.25F - this.cardHover[a]) / 3.0F * partialTicks, 0.0025F); } else { this.cardHover[a] = this.cardHover[a] - 0.1F * partialTicks; }   if (a == sz - 1 || this.cardZoomOut[a + 1] > 0.6D) { float f = this.cardZoomOut[a]; this.cardZoomOut[a] = this.cardZoomOut[a] + Math.max((1.0F - this.cardZoomOut[a]) / 5.0F * partialTicks, 0.0025F); if (this.cardZoomOut[a] > 0.0F && f == 0.0F) playButtonPageFlip();  }  float prevZoomIn = this.cardZoomIn[a]; if (this.cardSelected) { this.cardZoomIn[a] = (float)(this.cardZoomIn[a] + (this.cardActive[a] ? Math.max(((1.0F - this.cardZoomIn[a]) / 3.0F * partialTicks), 0.0025D) : (0.3F * partialTicks))); this.cardHover[a] = 1.0F - this.cardZoomIn[a]; }  this.cardZoomIn[a] = MathHelper.func_76131_a(this.cardZoomIn[a], 0.0F, 1.0F); this.cardHover[a] = MathHelper.func_76131_a(this.cardHover[a], 0.0F, 0.25F); this.cardZoomOut[a] = MathHelper.func_76131_a(this.cardZoomOut[a], 0.0F, 1.0F); float dx = (55 + sx - 55 * sz + xx + cw * a - xx + 65); float fx = (xx + 65) + dx * this.cardZoomOut[a]; float qx = (xx + 191) - fx; if (this.cardActive[a]) fx += qx * this.cardZoomIn[a];  drawSheet(fx, (yy + 100), (6.0F + this.cardZoomOut[a] * 2.0F - this.cardZoomIn[a] * 2.0F + this.cardHover[a]), r, this.cardActive[a] ? 1.0F : (1.0F - this.cardZoomIn[a]), Math.max(1.0F - this.cardZoomOut[a], this.cardZoomIn[a]), cardChoice); if (this.cardSelected && this.cardActive[a] && this.cardZoomIn[a] >= 1.0F && prevZoomIn < 1.0F) { playButtonWrite(); this.cardChoices.clear(); this.cardSelected = false; this.lastDraw = this.table.data.lastDraw; break; }  a++; }  }  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); RenderHelper.func_74518_a(); }
/*     */   private void drawSheet(double x, double y, double scale, Random r, float alpha, float tilt, ResearchTableData.CardChoice cardChoice) { GL11.glPushMatrix(); GlStateManager.func_179147_l(); GlStateManager.func_179112_b(770, 771); GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha); GL11.glTranslated(x + r.nextGaussian(), y + r.nextGaussian(), 0.0D); GL11.glScaled(scale, scale, 0.0D); GL11.glRotated(r.nextGaussian() * tilt, 0.0D, 0.0D, 1.0D); GL11.glPushMatrix(); if (cardChoice != null && cardChoice.fromAid) { this.field_146297_k.field_71446_o.func_110577_a(this.txPaperGilded); } else { this.field_146297_k.field_71446_o.func_110577_a(this.txPaper); }  if (r.nextBoolean())
/*     */       GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);  if (r.nextBoolean())
/* 807 */       GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);  GL11.glDisable(2884); UtilsFX.drawTexturedQuadFull(-8.0F, -8.0F, 0.0D); GL11.glEnable(2884); GL11.glPopMatrix(); if (cardChoice != null && alpha == 1.0F) { if (cardChoice.card.getResearchCategory() != null) { ResearchCategory rc = ResearchCategories.getResearchCategory(cardChoice.card.getResearchCategory()); if (rc != null) { GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha / 6.0F); GL11.glPushMatrix(); GL11.glScaled(0.5D, 0.5D, 0.0D); this.field_146297_k.field_71446_o.func_110577_a(rc.icon); UtilsFX.drawTexturedQuadFull(-8.0F, -8.0F, 0.0D); GL11.glPopMatrix(); }  }  GL11.glPushMatrix(); GL11.glScaled(0.0625D, 0.0625D, 0.0D); GL11.glColor4f(0.0F, 0.0F, 0.0F, alpha); String name = TextFormatting.BOLD + cardChoice.card.getLocalizedName() + TextFormatting.RESET; int sz = this.field_146289_q.func_78256_a(name); this.field_146289_q.func_78276_b(name, -sz / 2, -65, 0); this.field_146289_q.func_78279_b(cardChoice.card.getLocalizedText(), -70, -48, 140, 0); GL11.glPopMatrix(); GL11.glPushMatrix(); this.field_146297_k.field_71446_o.func_110577_a(this.txBase); GL11.glScaled(0.0625D, 0.0625D, 0.0D); int cc = cardChoice.card.getInspirationCost(); boolean add = false; if (cc < 0) { add = true; cc = Math.abs(cc) + 1; }  GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha); for (int a = 0; a < cc; a++) { if (a == 0 && add) { func_73729_b(-10 * cc + 20 * a, -95, 48, 0, 16, 16); } else { func_73729_b(-10 * cc + 20 * a, -95, 32, 96, 16, 16); }  }  GL11.glPopMatrix(); if (cardChoice.card.getRequiredItems() != null) { ItemStack[] items = cardChoice.card.getRequiredItems(); GL11.glPushMatrix(); for (int a = 0; a < items.length; a++) { if (items[a] == null || items[a].func_190926_b()) { GL11.glPushMatrix(); this.field_146297_k.field_71446_o.func_110577_a(this.txQuestion); GL11.glScaled(0.125D, 0.125D, 0.0D); GL11.glColor4f(0.75F, 0.75F, 0.75F, alpha); GL11.glTranslated((-9 * items.length + 18 * a), 35.0D, 0.0D); UtilsFX.drawTexturedQuadFull(0.0F, 0.0F, 0.0D); GL11.glPopMatrix(); } else { GL11.glPushMatrix(); GL11.glScaled(0.125D, 0.125D, 0.0D); RenderHelper.func_74520_c(); GlStateManager.func_179140_f(); GlStateManager.func_179091_B(); GlStateManager.func_179142_g(); GlStateManager.func_179145_e(); this.field_146296_j.func_180450_b(items[a], -9 * items.length + 18 * a, 35); GlStateManager.func_179140_f(); GlStateManager.func_179132_a(true); GlStateManager.func_179126_j(); GL11.glPopMatrix(); try { if (cardChoice.card.getRequiredItemsConsumed()[a]) { GL11.glPushMatrix(); this.field_146297_k.field_71446_o.func_110577_a(this.txBase); GL11.glScaled(0.125D, 0.125D, 0.0D); float s = (float)Math.sin((((this.player.field_70173_aa + a * 2) + this.field_146297_k.func_184121_ak()) / 2.0F)) * 0.03F; GL11.glTranslated((-2 - 9 * items.length + 18 * a), (45.0F + s * 10.0F), 0.0D); GL11.glScaled(0.5D, 0.5D + s, 0.0D); func_73729_b(0, 0, 64, 120, 16, 16); GL11.glPopMatrix(); }  } catch (Exception exception) {} }  }  GL11.glPopMatrix(); }  }  GlStateManager.func_179084_k(); GL11.glPopMatrix(); } void checkCards() { if (this.table.data.cardChoices.size() > 0 && this.cardChoices.isEmpty()) {
/* 808 */       syncFromTableChoices();
/*     */     }
/*     */     
/* 811 */     if (!this.cardSelected)
/* 812 */       for (int a = 0; a < this.cardChoices.size(); a++)
/*     */       { try {
/* 814 */           if (this.table.data != null && this.table.data.cardChoices.size() > a && ((ResearchTableData.CardChoice)this.table.data.cardChoices.get(a)).selected) {
/* 815 */             for (int q = 0; q < this.cardChoices.size(); q++)
/* 816 */               this.cardActive[q] = ((ResearchTableData.CardChoice)this.table.data.cardChoices.get(q)).selected; 
/* 817 */             this.cardSelected = true;
/* 818 */             playButtonPageSelect();
/* 819 */             this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
/*     */             
/*     */             break;
/*     */           } 
/* 823 */         } catch (Exception exception) {} }   } private void drawSheetOverlay(double x, double y, ResearchTableData.CardChoice cardChoice, int mx, int my) { GL11.glPushMatrix(); if (cardChoice != null && cardChoice.card.getRequiredItems() != null) { ItemStack[] items = cardChoice.card.getRequiredItems(); for (int a = 0; a < items.length; a++) { if (func_146978_c((int)(x - (9 * items.length) + (18 * a)), (int)(y + 36.0D), 15, 15, mx, my)) if (items[a] == null || items[a].func_190926_b()) { func_146283_a(Arrays.asList(new String[] { I18n.func_74838_a("tc.card.unknown") }, ), mx, my); } else { func_146285_a(items[a], mx, my); }   }  }  GL11.glPopMatrix(); } private void drawCards() { this.cardSelected = false; this.cardHover = new float[] { 0.0F, 0.0F, 0.0F }; this.cardZoomOut = new float[] { 0.0F, 0.0F, 0.0F }; this.cardZoomIn = new float[] { 0.0F, 0.0F, 0.0F }; this.cardActive = new boolean[] { true, true, true }; int draw = 2; if (this.table.data.bonusDraws > 0) { draw++; this.table.data.bonusDraws--; }  this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, draw); this.cardChoices.clear(); }
/*     */   public void func_73866_w_() { super.func_73866_w_(); this.field_146292_n.add(this.buttonCreate); this.buttonCreate.field_146128_h = this.field_147003_i + 128; this.buttonCreate.field_146129_i = this.field_147009_r + 22; this.field_146292_n.add(this.buttonComplete); this.buttonComplete.field_146128_h = this.field_147003_i + 191; this.buttonComplete.field_146129_i = this.field_147009_r + 96; this.field_146292_n.add(this.buttonScrap); this.buttonScrap.field_146128_h = this.field_147003_i + 128; this.buttonScrap.field_146129_i = this.field_147009_r + 168; }
/*     */   protected void func_146284_a(GuiButton button) throws IOException { if (button.field_146127_k == 1) { playButtonClick(); PacketHandler.INSTANCE.sendToServer(new PacketStartTheoryToServer(this.table.func_174877_v(), this.selectedAids)); } else if (button.field_146127_k == 7) { playButtonClick(); this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 7); this.tempCatTotals.clear(); this.lastDraw = null; } else if (button.field_146127_k == 9) { playButtonClick(); this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 9); this.tempCatTotals.clear(); this.lastDraw = null; this.table.data = null; this.cardChoices.clear(); } else { super.func_146284_a(button); }  }
/*     */   private void checkButtons() { this.buttonComplete.active = false; this.buttonComplete.field_146125_m = false; this.buttonScrap.active = false; this.buttonScrap.field_146125_m = false; if (this.table.data != null) { this.buttonCreate.active = false; this.buttonCreate.field_146125_m = false; if (this.table.data.isComplete()) { this.buttonComplete.active = true; this.buttonComplete.field_146125_m = true; } else { this.buttonScrap.active = true; this.buttonScrap.field_146125_m = true; }  } else { this.buttonCreate.field_146125_m = true; if (this.table.func_70301_a(true) == null || this.table.func_70301_a(false) == null || this.table.func_70301_a(0).func_77952_i() == this.table.func_70301_a(0).func_77958_k()) { this.buttonCreate.active = false; } else { this.buttonCreate.active = true; }  }  }
/*     */   protected void func_73864_a(int mx, int my, int par3) throws IOException { super.func_73864_a(mx, my, par3); int xx = (this.field_146294_l - this.field_146999_f) / 2; int yy = (this.field_146295_m - this.field_147000_g) / 2; if (this.table.data == null) { if (!this.currentAids.isEmpty()) { int side = Math.min(this.currentAids.size(), 6); int c = 0; int r = 0; for (String key : this.currentAids) { ITheorycraftAid mutator = (ITheorycraftAid)TheorycraftManager.aids.get(key); if (mutator == null) continue;  int x = 128 + 20 * c - side * 10; int y = 85 + 35 * r; if (func_146978_c(x, y, 16, 16, mx, my)) if (this.selectedAids.contains(key)) { this.selectedAids.remove(key); } else if (this.selectedAids.size() + 1 < this.dummyInspirationStart) { this.selectedAids.add(key); }   c++; if (c >= side) { r++; c = 0; }  }  }  } else { int sx = 128; int cw = 110; if (this.cardChoices.size() > 0) { int pressed = -1; for (int a = 0; a < this.cardChoices.size(); a++) { int var7 = mx - 5 + sx - 55 * this.cardChoices.size() + xx + cw * a; int var8 = my - 100 + yy - 60; if (this.cardZoomOut[a] >= 0.95D && !this.cardSelected && var7 >= 0 && var8 >= 0 && var7 < 100 && var8 < 120) { pressed = a; break; }  }  if (pressed >= 0 && this.table.func_70301_a(false) != null && this.table.func_70301_a(0).func_77952_i() != this.table.func_70301_a(0).func_77958_k()) this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 4 + pressed);  } else { int var7 = mx - 25 + xx; int var8 = my - 55 + yy; if (var7 >= 0 && var8 >= 0 && var7 < 75 && var8 < 90 && this.table.func_70301_a(true) != null) drawCards();  }  }  }
/* 828 */   private void playButtonPageFlip() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.page, 1.0F, 1.0F); }
/*     */ 
/*     */ 
/*     */   
/* 832 */   private void playButtonPageSelect() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.pageturn, 1.0F, 1.0F); }
/*     */ 
/*     */ 
/*     */   
/* 836 */   private void playButtonClick() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.clack, 0.4F, 1.0F); }
/*     */ 
/*     */ 
/*     */   
/* 840 */   private void playButtonWrite() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.write, 0.3F, 1.0F); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiResearchTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */