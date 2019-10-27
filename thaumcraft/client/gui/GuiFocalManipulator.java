/*      */ package thaumcraft.client.gui;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.io.IOException;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.gui.GuiButton;
/*      */ import net.minecraft.client.gui.GuiTextField;
/*      */ import net.minecraft.client.gui.inventory.GuiContainer;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.text.TextFormatting;
/*      */ import net.minecraft.util.text.translation.I18n;
/*      */ import net.minecraftforge.fml.relauncher.Side;
/*      */ import net.minecraftforge.fml.relauncher.SideOnly;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import thaumcraft.api.ThaumcraftApiHelper;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*      */ import thaumcraft.api.casters.FocusEffect;
/*      */ import thaumcraft.api.casters.FocusEngine;
/*      */ import thaumcraft.api.casters.FocusMedium;
/*      */ import thaumcraft.api.casters.FocusNode;
/*      */ import thaumcraft.api.casters.IFocusElement;
/*      */ import thaumcraft.api.casters.NodeSetting;
/*      */ import thaumcraft.client.gui.plugins.GuiFocusSettingSpinnerButton;
/*      */ import thaumcraft.client.gui.plugins.GuiHoverButton;
/*      */ import thaumcraft.client.gui.plugins.GuiImageButton;
/*      */ import thaumcraft.client.gui.plugins.GuiSliderTC;
/*      */ import thaumcraft.client.lib.UtilsFX;
/*      */ import thaumcraft.common.container.ContainerFocalManipulator;
/*      */ import thaumcraft.common.items.casters.ItemFocus;
/*      */ import thaumcraft.common.lib.SoundsTC;
/*      */ import thaumcraft.common.lib.network.PacketHandler;
/*      */ import thaumcraft.common.lib.network.playerdata.PacketFocusNameToServer;
/*      */ import thaumcraft.common.lib.network.playerdata.PacketFocusNodesToServer;
/*      */ import thaumcraft.common.lib.utils.InventoryUtils;
/*      */ import thaumcraft.common.tiles.crafting.FocusElementNode;
/*      */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*      */ 
/*      */ @SideOnly(Side.CLIENT)
/*      */ public class GuiFocalManipulator extends GuiContainer {
/*      */   private TileFocalManipulator table;
/*      */   private float xSize_lo;
/*      */   private float ySize_lo;
/*      */   private int isMouseButtonDown;
/*      */   protected int mouseX;
/*      */   protected int mouseY;
/*      */   protected double curMouseX;
/*      */   protected double curMouseY;
/*      */   ResourceLocation tex;
/*      */   ResourceLocation tex2;
/*      */   ResourceLocation tex3;
/*      */   ResourceLocation texbase;
/*      */   
/*      */   public GuiFocalManipulator(InventoryPlayer par1InventoryPlayer, TileFocalManipulator table) {
/*   68 */     super(new ContainerFocalManipulator(par1InventoryPlayer, table));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   82 */     this.isMouseButtonDown = 0;
/*   83 */     this.mouseX = 0;
/*   84 */     this.mouseY = 0;
/*      */ 
/*      */ 
/*      */     
/*   88 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_wandtable.png");
/*   89 */     this.tex2 = new ResourceLocation("thaumcraft", "textures/gui/gui_wandtable2.png");
/*   90 */     this.tex3 = new ResourceLocation("thaumcraft", "textures/gui/gui_wandtable3.png");
/*   91 */     this.texbase = new ResourceLocation("thaumcraft", "textures/gui/gui_base.png");
/*      */     
/*   93 */     this
/*   94 */       .buttonConfirm = new GuiImageButton(this, 0, this.field_147003_i + 232, this.field_147009_r + 7, 24, 16, null, I18n.func_74838_a("wandtable.text3"), this.texbase, 232, 240, 24, 16);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  386 */     this.totalComplexity = 0;
/*  387 */     this.maxComplexity = 0;
/*  388 */     this.lastNodeHover = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  535 */     this.myFormatter = new DecimalFormat("#######.##");
/*      */     
/*  537 */     this.shownParts = new ArrayList();
/*      */     
/*  539 */     this.partsStart = 0;
/*      */     
/*  541 */     this.components = null;
/*      */     
/*  543 */     this.valid = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  775 */     this.nodeID = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  969 */     this.sMinX = 0;
/*  970 */     this.sMinY = 0;
/*  971 */     this.sMaxX = 0;
/*  972 */     this.sMaxY = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  989 */     this.selectedNode = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1062 */     this.costCast = 0.0F;
/* 1063 */     this.costXp = 0;
/* 1064 */     this.costVis = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1230 */     this.scrollX = 0;
/* 1231 */     this.scrollY = 0;
/*      */     this.table = table;
/*      */     this.field_146999_f = 231;
/*      */     this.field_147000_g = 231;
/*      */   }
/*      */   
/*      */   GuiImageButton buttonConfirm;
/*      */   GuiSliderTC scrollbarParts;
/*      */   GuiSliderTC scrollbarMainSide;
/*      */   GuiSliderTC scrollbarMainBottom;
/*      */   private GuiTextField nameField;
/*      */   int totalComplexity;
/*      */   int maxComplexity;
/*      */   int lastNodeHover;
/*      */   DecimalFormat myFormatter;
/*      */   ArrayList<String> shownParts;
/*      */   int partsStart;
/*      */   ItemStack[] components;
/*      */   boolean valid;
/*      */   
/*      */   public void func_73866_w_() {
/*      */     super.func_73866_w_();
/*      */     gatherInfo(false);
/*      */     setupNameField();
/*      */     if (this.table.focusName.isEmpty() && !this.table.func_70301_a(0).func_190926_b())
/*      */       this.table.focusName = this.table.func_70301_a(0).func_82833_r(); 
/*      */     this.nameField.func_146180_a(this.table.focusName);
/*      */   }
/*      */   
/*      */   private void setupNameField() {
/*      */     Keyboard.enableRepeatEvents(true);
/*      */     int i = (this.field_146294_l - this.field_146999_f) / 2;
/*      */     int j = (this.field_146295_m - this.field_147000_g) / 2;
/*      */     this.nameField = new GuiTextField(2, this.field_146289_q, i + 30, j + 11, 170, 12);
/*      */     this.nameField.func_146193_g(-1);
/*      */     this.nameField.func_146204_h(-1);
/*      */     this.nameField.func_146185_a(false);
/*      */     this.nameField.func_146203_f(50);
/*      */   }
/*      */   
/*      */   protected void func_146284_a(GuiButton button) throws IOException {
/*      */     if (button.field_146127_k == 0 && this.buttonConfirm != null && this.buttonConfirm.active) {
/*      */       this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 0);
/*      */     } else if (button.field_146127_k >= 10) {
/*      */       gatherInfo(true);
/*      */     } else {
/*      */       super.func_146284_a(button);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void func_73869_a(char typedChar, int keyCode) throws IOException {
/*      */     if (this.nameField.func_146201_a(typedChar, keyCode)) {
/*      */       this.table.focusName = this.nameField.func_146179_b();
/*      */       PacketHandler.INSTANCE.sendToServer(new PacketFocusNameToServer(this.table.func_174877_v(), this.table.focusName));
/*      */     } else {
/*      */       super.func_73869_a(typedChar, keyCode);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void func_73863_a(int mx, int my, float par3) {
/*      */     func_146276_q_();
/*      */     super.func_73863_a(mx, my, par3);
/*      */     GlStateManager.func_179147_l();
/*      */     GL11.glBlendFunc(770, 771);
/*      */     this.xSize_lo = mx;
/*      */     this.ySize_lo = my;
/*      */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/*      */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*      */     if (Mouse.isButtonDown(0)) {
/*      */       if ((this.isMouseButtonDown == 0 || this.isMouseButtonDown == 1) && func_146978_c(this.field_147003_i + 63, this.field_147009_r + 31, 136, 160, mx + this.field_147003_i, my + this.field_147009_r))
/*      */         if (this.isMouseButtonDown == 0) {
/*      */           this.isMouseButtonDown = 1;
/*      */         } else {
/*      */           boolean b = (this.scrollbarParts != null && this.scrollbarParts.func_146115_a());
/*      */           if (this.scrollbarMainBottom != null && !this.scrollbarMainBottom.func_146115_a() && !b) {
/*      */             this.scrollX = (int)(this.scrollX - (mx - this.mouseX));
/*      */             this.curMouseX = this.scrollX;
/*      */             if (this.scrollX > this.scrollbarMainBottom.getMax())
/*      */               this.scrollX = (int)this.scrollbarMainBottom.getMax(); 
/*      */             if (this.scrollX < this.scrollbarMainBottom.getMin())
/*      */               this.scrollX = (int)this.scrollbarMainBottom.getMin(); 
/*      */             this.scrollbarMainBottom.setSliderValue(this.scrollX, false);
/*      */           } 
/*      */           if (this.scrollbarMainSide != null && !this.scrollbarMainSide.func_146115_a() && !b) {
/*      */             this.scrollY = (int)(this.scrollY - (my - this.mouseY));
/*      */             this.curMouseY = this.scrollY;
/*      */             if (this.scrollY > this.scrollbarMainSide.getMax())
/*      */               this.scrollY = (int)this.scrollbarMainSide.getMax(); 
/*      */             if (this.scrollY < this.scrollbarMainSide.getMin())
/*      */               this.scrollY = (int)this.scrollbarMainSide.getMin(); 
/*      */             this.scrollbarMainSide.setSliderValue(this.scrollY, false);
/*      */           } 
/*      */         }  
/*      */       this.mouseX = mx;
/*      */       this.mouseY = my;
/*      */     } else {
/*      */       this.isMouseButtonDown = 0;
/*      */     } 
/*      */     RenderHelper.func_74518_a();
/*      */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     int count = 0;
/*      */     int index = 0;
/*      */     for (String sk : this.shownParts) {
/*      */       count++;
/*      */       if (count - 1 < this.partsStart)
/*      */         continue; 
/*      */       GL11.glTranslated(0.0D, 0.0D, 5.0D);
/*      */       FocusNode node = (FocusNode)FocusEngine.getElement(sk);
/*      */       drawPart(node, gx + 38, 43 + gy + 25 * index, (node.getType() == IFocusElement.EnumUnitType.MOD) ? 24.0F : 32.0F, 220, func_146978_c(gx + 38 - 10, 32 + gy + 24 * index, 20, 20, mx + this.field_147003_i, my + this.field_147009_r));
/*      */       GL11.glTranslated(0.0D, 0.0D, -5.0D);
/*      */       index++;
/*      */       if (index > 5)
/*      */         break; 
/*      */     } 
/*      */     count = 0;
/*      */     index = 0;
/*      */     for (String sk : this.shownParts) {
/*      */       count++;
/*      */       if (count - 1 < this.partsStart)
/*      */         continue; 
/*      */       FocusNode node = (FocusNode)FocusEngine.getElement(sk);
/*      */       if (func_146978_c(gx + 38 - 10, 33 + gy + 25 * index, 20, 20, mx + this.field_147003_i, my + this.field_147009_r)) {
/*      */         List list = genPartText(node, -1);
/*      */         drawHoveringTextFixed(list, gx + 44, 46 + gy + 24 * index, this.field_146289_q, this.field_146294_l - this.field_147003_i + this.field_146999_f - 16);
/*      */       } 
/*      */       index++;
/*      */       if (index > 5)
/*      */         break; 
/*      */     } 
/*      */     if (this.lastNodeHover >= 0) {
/*      */       FocusElementNode fn = (FocusElementNode)this.table.data.get(Integer.valueOf(this.lastNodeHover));
/*      */       if (fn != null && fn.node != null) {
/*      */         int xx = this.field_147003_i + 140 - this.scrollX + fn.x * 24;
/*      */         int yy = this.field_147009_r + 50 - this.scrollY + fn.y * 32;
/*      */         List list = genPartText(fn.node, this.lastNodeHover);
/*      */         drawHoveringTextFixed(list, xx, yy, this.field_146289_q, this.field_146294_l - this.field_147003_i + this.field_146999_f - 16);
/*      */       } 
/*      */     } 
/*      */     this.buttonConfirm.active = (this.table.vis <= 0.0F && this.valid);
/*      */     GlStateManager.func_179084_k();
/*      */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     if (this.table.data != null && !this.table.data.isEmpty() && this.nameField != null)
/*      */       this.nameField.func_146194_f(); 
/*      */     func_191948_b(mx, my);
/*      */   }
/*      */   
/*      */   private List genPartText(FocusNode node, int idx) {
/*      */     List list = new ArrayList();
/*      */     if (node != null) {
/*      */       FocusElementNode placed = null;
/*      */       if (idx >= 0)
/*      */         placed = (FocusElementNode)this.table.data.get(Integer.valueOf(idx)); 
/*      */       list.add(I18n.func_74838_a(node.getUnlocalizedName()));
/*      */       list.add(TextFormatting.DARK_PURPLE + I18n.func_74838_a(node.getUnlocalizedText()));
/*      */       int c = node.getComplexity();
/*      */       if (placed != null)
/*      */         c = (int)(node.getComplexity() * placed.complexityMultiplier); 
/*      */       list.add(TextFormatting.GOLD + I18n.func_74838_a("focuspart.com") + ((placed != null && placed.complexityMultiplier > 1.0F) ? TextFormatting.RED : "") + " " + c);
/*      */       float p = node.getPowerMultiplier();
/*      */       if (placed != null)
/*      */         p = placed.getPower(this.table.data); 
/*      */       if (p != 1.0F)
/*      */         list.add(TextFormatting.GOLD + I18n.func_74838_a("focuspart.eff") + ((p < 1.0F) ? TextFormatting.RED : TextFormatting.GREEN) + " x" + ItemStack.field_111284_a.format(p)); 
/*      */       if (node instanceof FocusEffect) {
/*      */         FocusEffect fe = (FocusEffect)node;
/*      */         float d = fe.getDamageForDisplay((placed == null) ? 1.0F : placed.getPower(this.table.data));
/*      */         if (d > 0.0F) {
/*      */           list.add(TextFormatting.DARK_RED + "" + I18n.func_74837_a("attribute.modifier.equals.0", new Object[] { ItemStack.field_111284_a.format(d), I18n.func_74838_a("attribute.name.generic.attackDamage") }));
/*      */         } else if (d < 0.0F) {
/*      */           d *= -1.0F;
/*      */           list.add(TextFormatting.DARK_GREEN + "" + I18n.func_74837_a("attribute.modifier.equals.0", new Object[] { ItemStack.field_111284_a.format(d), I18n.func_74838_a("focus.heal.power") }));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     return list;
/*      */   }
/*      */   
/*      */   protected void func_146976_a(float par1, int par2, int par3) {
/*      */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     this.field_146297_k.field_71446_o.func_110577_a(this.tex2);
/*      */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/*      */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*      */     GL11.glEnable(3042);
/*      */     GL11.glBlendFunc(770, 771);
/*      */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*      */     this.field_146297_k.field_71446_o.func_110577_a(this.tex3);
/*      */     func_73729_b(k - 71, l - 3, 0, 0, 71, 239);
/*      */     if (this.table.func_70301_a(false) == null || this.table.func_70301_a(0).func_190926_b() || this.table.doGuiReset) {
/*      */       if (this.table.doGuiReset) {
/*      */         resetNodes();
/*      */       } else {
/*      */         this.table.data.clear();
/*      */         gatherInfo(false);
/*      */       } 
/*      */       this.table.focusName = "";
/*      */       if (this.table.func_70301_a(false) != null && !this.table.func_70301_a(0).func_190926_b()) {
/*      */         this.table.focusName = this.table.func_70301_a(0).func_82833_r();
/*      */         this.nameField.func_146180_a(this.table.focusName);
/*      */       } 
/*      */       this.table.doGuiReset = false;
/*      */     } 
/*      */     if (this.table.doGather) {
/*      */       gatherInfo(false);
/*      */       this.table.doGather = false;
/*      */     } 
/*      */     drawNodes(this.field_147003_i + 132 - this.scrollX, this.field_147009_r + 48 - this.scrollY, par2, par3);
/*      */     GL11.glTranslated(0.0D, 0.0D, 1.0D);
/*      */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*      */     GL11.glEnable(3042);
/*      */     GL11.glBlendFunc(770, 771);
/*      */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*      */     if (this.maxComplexity > 0)
/*      */       this.field_146289_q.func_175063_a(this.totalComplexity + "/" + this.maxComplexity, (k + 242), (l + 36), (this.totalComplexity > this.maxComplexity) ? 16151160 : 16761407); 
/*      */     this.field_146289_q.func_175063_a("" + this.costXp, (k + 242), (l + 50), (this.costXp > this.field_146297_k.field_71439_g.field_71068_ca) ? 16151160 : 10092429);
/*      */     int v = this.costVis;
/*      */     if (this.table.vis > 0.0F)
/*      */       v = (int)this.table.vis; 
/*      */     this.field_146289_q.func_175063_a(TextFormatting.AQUA + "" + v, (k + 242), (l + 64), 10092429);
/*      */     if (this.costCast > 0.0F) {
/*      */       String cost = this.myFormatter.format(this.costCast);
/*      */       this.field_146289_q.func_175063_a(TextFormatting.AQUA + I18n.func_74838_a("item.Focus.cost1") + ": " + cost, (k + 230), (l + 80), 10092429);
/*      */     } 
/*      */     if (this.components != null && this.components.length > 0)
/*      */       this.field_146289_q.func_175063_a(TextFormatting.GOLD + I18n.func_74838_a("wandtable.text4"), (k + 230), (l + 92), 10092429); 
/*      */     if (this.table.focusName != null && !this.table.data.isEmpty()) {
/*      */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       this.field_146297_k.field_71446_o.func_110577_a(this.texbase);
/*      */       func_73729_b(this.field_147003_i + 24, this.field_147009_r + 8, 192, 224, 8, 14);
/*      */       int a = 1;
/*      */       for (a = 1; a < 22; ) {
/*      */         func_73729_b(this.field_147003_i + 24 + a * 8, this.field_147009_r + 8, 200, 224, 8, 14);
/*      */         a++;
/*      */       } 
/*      */       func_73729_b(this.field_147003_i + 24 + a * 8, this.field_147009_r + 8, 208, 224, 8, 14);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void drawClippedRect(int x, int y, int sx, int sy, int w, int h) {
/*      */     if (func_146978_c(48, 26, 166, 174, x + w / 2, y + h / 2))
/*      */       func_73729_b(x, y, sx, sy, w, h); 
/*      */   }
/*      */   
/*      */   private void drawNodes(int x, int y, int mx, int my) {
/*      */     GL11.glPushMatrix();
/*      */     GL11.glEnable(3042);
/*      */     GL11.glBlendFunc(770, 771);
/*      */     GlStateManager.func_179140_f();
/*      */     if (this.table.data != null && !this.table.data.isEmpty()) {
/*      */       int hover = -1;
/*      */       for (FocusElementNode fn : this.table.data.values()) {
/*      */         int xx = x + fn.x * 24;
/*      */         int yy = y + fn.y * 32;
/*      */         boolean mouseover = (func_146978_c(this.field_147003_i + 63, this.field_147009_r + 31, 136, 160, mx + this.field_147003_i, my + this.field_147009_r) && func_146978_c(xx - 10, yy - 10, 20, 20, mx + this.field_147003_i, my + this.field_147009_r));
/*      */         if (mouseover && fn.parent >= 0)
/*      */           hover = fn.id; 
/*      */         if (fn.node != null) {
/*      */           if (func_146978_c(48, 16, 154, 192, xx - 8, yy - 8))
/*      */             drawPart(fn.node, xx, yy, 32.0F, 220, mouseover); 
/*      */         } else {
/*      */           this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*      */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */           drawClippedRect(xx - 12, yy - 12, 120, 232, 24, 24);
/*      */         } 
/*      */         if (this.selectedNode == fn.id || (mouseover && fn.parent >= 0)) {
/*      */           this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*      */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */           drawClippedRect(xx - 12, yy - 12, 96, 232, 24, 24);
/*      */         } 
/*      */         FocusElementNode parent = (FocusElementNode)this.table.data.get(Integer.valueOf(fn.parent));
/*      */         if (parent != null) {
/*      */           this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/*      */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */           drawClippedRect(xx - 6, yy - 22, 54, 232, 12, 12);
/*      */           if (parent.node instanceof thaumcraft.api.casters.FocusModSplit) {
/*      */             int q = Math.abs(fn.x - parent.x);
/*      */             for (int a = 0; a < q; a++) {
/*      */               if (fn.x < parent.x) {
/*      */                 if (a == 0) {
/*      */                   drawClippedRect(xx - 4, yy - 36, 8, 240, 16, 16);
/*      */                 } else {
/*      */                   drawClippedRect(xx - 12 + a * 24, yy - 36, 72, 240, 24, 16);
/*      */                 } 
/*      */               } else if (a == 0) {
/*      */                 drawClippedRect(xx - 12, yy - 36, 24, 240, 16, 16);
/*      */               } else {
/*      */                 drawClippedRect(xx - 12 - a * 24, yy - 36, 72, 240, 24, 16);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           if (fn.node == null) {
/*      */             int s = (parent.target && parent.trajectory) ? 4 : 0;
/*      */             if (func_146978_c(48, 16, 168, 192, xx - 4, yy - 4)) {
/*      */               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */               if (parent.target) {
/*      */                 GL11.glPushMatrix();
/*      */                 GL11.glTranslated((xx - s), yy, 0.0D);
/*      */                 GL11.glScaled(0.5D, 0.5D, 0.5D);
/*      */                 func_73729_b(-8, -8, 152, 240, 16, 16);
/*      */                 GL11.glPopMatrix();
/*      */               } 
/*      */               if (parent.trajectory) {
/*      */                 GL11.glPushMatrix();
/*      */                 GL11.glTranslated((xx + s), yy, 0.0D);
/*      */                 GL11.glScaled(0.5D, 0.5D, 0.5D);
/*      */                 func_73729_b(-8, -8, 168, 240, 16, 16);
/*      */                 GL11.glPopMatrix();
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       if (hover >= 0 && this.lastNodeHover != hover) {
/*      */         playRollover();
/*      */         this.lastNodeHover = hover;
/*      */       } 
/*      */       if (hover < 0)
/*      */         this.lastNodeHover = -1; 
/*      */       if (this.selectedNode >= 0)
/*      */         drawNodeSettings(this.selectedNode); 
/*      */     } 
/*      */     GlStateManager.func_179145_e();
/*      */     RenderHelper.func_74518_a();
/*      */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   private void drawNodeSettings(int selectedNode2) {
/*      */     FocusElementNode fn = (FocusElementNode)this.table.data.get(Integer.valueOf(selectedNode2));
/*      */     if (fn != null && fn.node != null && !fn.node.getSettingList().isEmpty()) {
/*      */       int a = 0;
/*      */       for (String sk : fn.node.getSettingList()) {
/*      */         NodeSetting setting = fn.node.getSetting(sk);
/*      */         if (setting.getResearch() != null && !ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { setting.getResearch() }))
/*      */           continue; 
/*      */         int x = this.field_147003_i + this.field_146999_f;
/*      */         int y = this.field_147009_r + this.field_147000_g - 10 - fn.node.getSettingList().size() * 26 + a * 26;
/*      */         this.field_146289_q.func_175063_a(TextFormatting.GOLD + setting.getLocalizedName(), x, y, 16777215);
/*      */         a++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void func_146274_d() {
/*      */     super.func_146274_d();
/*      */     int k = Mouse.getDWheel();
/*      */     int i = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
/*      */     int j = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
/*      */     if (this.scrollbarParts != null && this.shownParts.size() > 6 && func_146978_c(24, 24, 32, 157, i, j))
/*      */       if (k > 0) {
/*      */         if (this.partsStart > 0) {
/*      */           this.partsStart--;
/*      */           this.scrollbarParts.setSliderValue(this.partsStart, false);
/*      */         } 
/*      */       } else if (k < 0) {
/*      */         if (this.partsStart < this.shownParts.size() - 6) {
/*      */           this.partsStart++;
/*      */           this.scrollbarParts.setSliderValue(this.partsStart, false);
/*      */         } 
/*      */       }  
/*      */   }
/*      */   
/*      */   protected void func_146979_b(int mouseX, int mouseY) {
/*      */     RenderHelper.func_74518_a();
/*      */     Iterator iterator = this.field_146292_n.iterator();
/*      */     while (iterator.hasNext()) {
/*      */       GuiButton guibutton = (GuiButton)iterator.next();
/*      */       if (guibutton.func_146115_a()) {
/*      */         guibutton.func_146111_b(mouseX - this.field_147003_i, mouseY - this.field_147009_r);
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     RenderHelper.func_74520_c();
/*      */     if (this.scrollbarMainSide != null) {
/*      */       int sv = Math.round(this.scrollbarMainSide.getSliderValue());
/*      */       if (sv != this.scrollY)
/*      */         this.scrollY = sv; 
/*      */     } 
/*      */     if (this.scrollbarMainBottom != null) {
/*      */       int sv = Math.round(this.scrollbarMainBottom.getSliderValue());
/*      */       if (sv != this.scrollX)
/*      */         this.scrollX = sv; 
/*      */     } 
/*      */     if (this.scrollbarParts != null) {
/*      */       int sv = Math.round(this.scrollbarParts.getSliderValue());
/*      */       if (sv != this.partsStart)
/*      */         this.partsStart = sv; 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void func_73864_a(int mx, int my, int par3) {
/*      */     try {
/*      */       super.func_73864_a(mx, my, par3);
/*      */     } catch (IOException iOException) {}
/*      */     int gx = (this.field_146294_l - this.field_146999_f) / 2;
/*      */     int gy = (this.field_146295_m - this.field_147000_g) / 2;
/*      */     if (this.table.vis <= 0.0F && this.table.data != null && !this.table.data.isEmpty()) {
/*      */       if (this.nameField != null)
/*      */         this.nameField.func_146192_a(mx, my, par3); 
/*      */       if (this.lastNodeHover >= 0) {
/*      */         this.selectedNode = this.lastNodeHover;
/*      */         boolean r = false;
/*      */         if (par3 == 1 && ((FocusElementNode)this.table.data.get(Integer.valueOf(this.selectedNode))).node != null) {
/*      */           FocusElementNode fn = (FocusElementNode)this.table.data.get(Integer.valueOf(this.selectedNode));
/*      */           if (((FocusElementNode)this.table.data.get(Integer.valueOf(fn.parent))).node != null)
/*      */             addNodeAt(((FocusElementNode)this.table.data.get(Integer.valueOf(fn.parent))).node.getClass(), fn.parent, true); 
/*      */         } 
/*      */         gatherInfo(false);
/*      */         playButtonClick();
/*      */       } 
/*      */       int count = 0;
/*      */       int index = 0;
/*      */       if (this.selectedNode >= 0)
/*      */         for (String sk : this.shownParts) {
/*      */           count++;
/*      */           if (count - 1 < this.partsStart)
/*      */             continue; 
/*      */           if (func_146978_c(gx + 28, gy + 32 + 24 * index, 20, 20, mx + this.field_147003_i, my + this.field_147009_r)) {
/*      */             addNodeAt((Class)FocusEngine.elements.get(sk), this.selectedNode, true);
/*      */             return;
/*      */           } 
/*      */           index++;
/*      */           if (index > 5)
/*      */             break; 
/*      */         }  
/*      */     } 
/*      */   }
/*      */   
/*      */   private void playButtonClick() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.clack, 0.4F, 1.0F); }
/*      */   
/*      */   private void playRollover() { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.clack, 0.4F, 2.0F); }
/*      */   
/*      */   private void playSocketSound(float pitch) { this.field_146297_k.func_175606_aa().func_184185_a(SoundsTC.crystal, 0.4F, pitch); }
/*      */   
/*      */   protected void drawHoveringTextFixed(List listin, int x, int y, FontRenderer font, int textWidth) {
/*      */     if (!listin.isEmpty()) {
/*      */       GlStateManager.func_179101_C();
/*      */       RenderHelper.func_74518_a();
/*      */       GlStateManager.func_179140_f();
/*      */       GlStateManager.func_179097_i();
/*      */       List list = new ArrayList();
/*      */       for (Object o : listin) {
/*      */         String s = (String)o;
/*      */         s = trimStringNewline(s);
/*      */         List list2 = font.func_78271_c(s, textWidth);
/*      */         for (Iterator iterator = list2.iterator(); iterator.hasNext(); ) {
/*      */           String s1 = (String)iterator.next();
/*      */           list.add(s1);
/*      */         } 
/*      */       } 
/*      */       int k = 0;
/*      */       Iterator iterator = list.iterator();
/*      */       while (iterator.hasNext()) {
/*      */         String s = (String)iterator.next();
/*      */         int l = font.func_78256_a(s);
/*      */         if (l > k)
/*      */           k = l; 
/*      */       } 
/*      */       int j2 = x + 12;
/*      */       int k2 = y - 12;
/*      */       int i1 = 8;
/*      */       if (list.size() > 1)
/*      */         i1 += 2 + (list.size() - 1) * 10; 
/*      */       this.field_73735_i = 300.0F;
/*      */       this.field_146296_j.field_77023_b = 300.0F;
/*      */       int j1 = -267386864;
/*      */       func_73733_a(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
/*      */       func_73733_a(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
/*      */       func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
/*      */       func_73733_a(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
/*      */       func_73733_a(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
/*      */       int k1 = 1347420415;
/*      */       int l1 = (k1 & 0xFEFEFE) >> 1 | k1 & 0xFF000000;
/*      */       func_73733_a(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
/*      */       func_73733_a(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
/*      */       func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
/*      */       func_73733_a(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);
/*      */       for (int i2 = 0; i2 < list.size(); i2++) {
/*      */         String s1 = (String)list.get(i2);
/*      */         font.func_175063_a(s1, j2, k2, -1);
/*      */         if (i2 == 0)
/*      */           k2 += 2; 
/*      */         k2 += 10;
/*      */       } 
/*      */       this.field_73735_i = 0.0F;
/*      */       this.field_146296_j.field_77023_b = 0.0F;
/*      */       GlStateManager.func_179091_B();
/*      */     } 
/*      */   }
/*      */   
/*      */   private String trimStringNewline(String text) {
/*      */     while (text != null && text.endsWith("\n"))
/*      */       text = text.substring(0, text.length() - 1); 
/*      */     return text;
/*      */   }
/*      */   
/*      */   static ResourceLocation iMedium = new ResourceLocation("thaumcraft", "textures/foci/_medium.png");
/*      */   static ResourceLocation iEffect = new ResourceLocation("thaumcraft", "textures/foci/_effect.png");
/*      */   private int nodeID;
/*      */   int sMinX;
/*      */   int sMinY;
/*      */   int sMaxX;
/*      */   int sMaxY;
/*      */   int selectedNode;
/*      */   float costCast;
/*      */   int costXp;
/*      */   int costVis;
/*      */   int scrollX;
/*      */   int scrollY;
/*      */   
/*      */   public static void drawPart(FocusNode node, int x, int y, float scale, int bright, boolean mouseover) {
/*      */     GL11.glPushMatrix();
/*      */     GL11.glAlphaFunc(516, 0.003921569F);
/*      */     GL11.glTranslated(x, y, 0.0D);
/*      */     GL11.glRotatef(90.0F, 0.0F, 0.0F, -1.0F);
/*      */     boolean root = (node.getType() == IFocusElement.EnumUnitType.MOD || node.getKey().equals("thaumcraft.ROOT"));
/*      */     if (root)
/*      */       scale *= 2.0F; 
/*      */     Color color = new Color(FocusEngine.getElementColor(node.getKey()));
/*      */     float r = color.getRed() / 255.0F;
/*      */     float g = color.getGreen() / 255.0F;
/*      */     float b = color.getBlue() / 255.0F;
/*      */     switch (node.getType()) {
/*      */       case EFFECT:
/*      */         UtilsFX.renderQuadCentered(iEffect, (float)(scale * 0.9D + (mouseover ? 2 : 0)), r, g, b, 220, 771, 1.0F);
/*      */         break;
/*      */       case MEDIUM:
/*      */         if (!root)
/*      */           UtilsFX.renderQuadCentered(iMedium, (float)(scale * 0.9D + (mouseover ? 2 : 0)), r, g, b, 220, 771, 1.0F); 
/*      */         break;
/*      */     } 
/*      */     GL11.glTranslated(0.0D, 0.0D, 1.0D);
/*      */     UtilsFX.renderQuadCentered(FocusEngine.getElementIcon(node.getKey()), scale / 2.0F + (mouseover ? 2 : 0), 1.0F, 1.0F, 1.0F, bright, 771, 1.0F);
/*      */     GL11.glAlphaFunc(516, 0.1F);
/*      */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   private int getNextId() {
/*      */     for (; this.table.data.containsKey(Integer.valueOf(this.nodeID)); this.nodeID++);
/*      */     return this.nodeID;
/*      */   }
/*      */   
/*      */   private void cullChildren(int idx) {
/*      */     if (this.table.data.containsKey(Integer.valueOf(idx)))
/*      */       for (int i : ((FocusElementNode)this.table.data.get(Integer.valueOf(idx))).children) {
/*      */         cullChildren(i);
/*      */         this.table.data.remove(Integer.valueOf(i));
/*      */       }  
/*      */   }
/*      */   
/*      */   private ArrayList<Integer> addNodeAt(Class nodeClass, int idx, boolean gather) {
/*      */     ArrayList<Integer> ret = new ArrayList<Integer>();
/*      */     boolean same = false;
/*      */     FocusElementNode previous = null;
/*      */     if (this.table.data.containsKey(Integer.valueOf(idx))) {
/*      */       cullChildren(idx);
/*      */       if (((FocusElementNode)this.table.data.get(Integer.valueOf(idx))).node != null && ((FocusElementNode)this.table.data.get(Integer.valueOf(idx))).node.getClass() == nodeClass) {
/*      */         same = true;
/*      */       } else {
/*      */         previous = (FocusElementNode)this.table.data.remove(Integer.valueOf(idx));
/*      */       } 
/*      */     } 
/*      */     try {
/*      */       FocusElementNode fn = null;
/*      */       FocusNode node = null;
/*      */       if (!same) {
/*      */         fn = new FocusElementNode();
/*      */         node = (FocusNode)nodeClass.newInstance();
/*      */         fn.node = node;
/*      */         if (previous != null) {
/*      */           fn.y = previous.y;
/*      */           fn.x = previous.x;
/*      */         } 
/*      */         fn.id = getNextId();
/*      */         ret.add(Integer.valueOf(fn.id));
/*      */         this.selectedNode = fn.id;
/*      */         if (previous != null && this.table.data.containsKey(Integer.valueOf(previous.parent))) {
/*      */           fn.parent = previous.parent;
/*      */           int[] c = ((FocusElementNode)this.table.data.get(Integer.valueOf(previous.parent))).children;
/*      */           for (int a = 0; a < c.length; a++) {
/*      */             if (c[a] == previous.id) {
/*      */               ((FocusElementNode)this.table.data.get(Integer.valueOf(previous.parent))).children[a] = fn.id;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         fn.target = node.canSupply(FocusNode.EnumSupplyType.TARGET);
/*      */         fn.trajectory = node.canSupply(FocusNode.EnumSupplyType.TRAJECTORY);
/*      */         this.table.data.put(Integer.valueOf(this.nodeID), fn);
/*      */       } else {
/*      */         fn = (FocusElementNode)this.table.data.get(Integer.valueOf(idx));
/*      */         node = fn.node;
/*      */       } 
/*      */       if (fn.target || fn.trajectory)
/*      */         if (node instanceof thaumcraft.api.casters.FocusModSplit) {
/*      */           FocusElementNode blank1 = new FocusElementNode();
/*      */           blank1.parent = fn.id;
/*      */           blank1.id = getNextId();
/*      */           ret.add(Integer.valueOf(this.nodeID));
/*      */           fn.x--;
/*      */           fn.y++;
/*      */           this.table.data.put(Integer.valueOf(this.nodeID), blank1);
/*      */           this.selectedNode = this.nodeID;
/*      */           FocusElementNode blank2 = new FocusElementNode();
/*      */           blank2.parent = fn.id;
/*      */           fn.x++;
/*      */           fn.y++;
/*      */           blank2.id = getNextId();
/*      */           ret.add(Integer.valueOf(this.nodeID));
/*      */           this.table.data.put(Integer.valueOf(this.nodeID), blank2);
/*      */           fn.children = new int[] { blank1.id, blank2.id };
/*      */         } else {
/*      */           FocusElementNode blank = new FocusElementNode();
/*      */           blank.parent = fn.id;
/*      */           blank.x = fn.x;
/*      */           fn.y++;
/*      */           blank.id = getNextId();
/*      */           ret.add(Integer.valueOf(this.nodeID));
/*      */           this.table.data.put(Integer.valueOf(this.nodeID), blank);
/*      */           fn.children = new int[] { blank.id };
/*      */           this.selectedNode = this.nodeID;
/*      */         }  
/*      */     } catch (Exception e) {
/*      */       e.printStackTrace();
/*      */     } 
/*      */     if (gather) {
/*      */       calcNodeTreeLayout();
/*      */       gatherInfo(true);
/*      */     } 
/*      */     return ret;
/*      */   }
/*      */   
/*      */   private class Bounds {
/*      */     private Bounds() {}
/*      */     
/*      */     int x = 0;
/*      */   }
/*      */   
/*      */   private void processLeftNodes(FocusElementNode start, Bounds bounds) {
/*      */     if (start.children.length > 0)
/*      */       processLeftNodes((FocusElementNode)this.table.data.get(Integer.valueOf(start.children[0])), bounds); 
/*      */     int ox = 0;
/*      */     if (start.children.length == 1) {
/*      */       ox = bounds.x - 1;
/*      */       bounds.x = ((FocusElementNode)this.table.data.get(Integer.valueOf(start.children[0]))).x;
/*      */     } 
/*      */     start.x = bounds.x;
/*      */     if (start.children.length == 1)
/*      */       bounds.x = ox; 
/*      */     bounds.x++;
/*      */     if (start.children.length > 1)
/*      */       processLeftNodes((FocusElementNode)this.table.data.get(Integer.valueOf(start.children[1])), bounds); 
/*      */   }
/*      */   
/*      */   private void centerNodes(int start, Bounds bounds) {
/*      */     int x = bounds.x / 2;
/*      */     FocusElementNode sn = (FocusElementNode)this.table.data.get(Integer.valueOf(start));
/*      */     moveNodes(sn, x);
/*      */   }
/*      */   
/*      */   private void moveNodes(FocusElementNode start, int amt) {
/*      */     for (int ci : start.children)
/*      */       moveNodes((FocusElementNode)this.table.data.get(Integer.valueOf(ci)), amt); 
/*      */     start.x -= amt;
/*      */   }
/*      */   
/*      */   private void calcNodeTreeLayout() {
/*      */     int fsi = -1;
/*      */     for (FocusElementNode node : this.table.data.values()) {
/*      */       if (fsi < 0 && node.node != null && node.node instanceof thaumcraft.api.casters.FocusModSplit)
/*      */         fsi = node.id; 
/*      */     } 
/*      */     if (fsi >= 0) {
/*      */       Bounds bounds = new Bounds(null);
/*      */       processLeftNodes((FocusElementNode)this.table.data.get(Integer.valueOf(fsi)), bounds);
/*      */       centerNodes(fsi, bounds);
/*      */     } 
/*      */     for (FocusElementNode node : this.table.data.values()) {
/*      */       if (node.node != null && node.node instanceof thaumcraft.api.casters.FocusModSplit) {
/*      */         if (this.table.data.containsKey(Integer.valueOf(node.parent)) && ((FocusElementNode)this.table.data.get(Integer.valueOf(node.parent))).node != null && !(((FocusElementNode)this.table.data.get(Integer.valueOf(node.parent))).node instanceof thaumcraft.api.casters.FocusModSplit)) {
/*      */           node.x = ((FocusElementNode)this.table.data.get(Integer.valueOf(node.parent))).x;
/*      */           continue;
/*      */         } 
/*      */         int xx = 0;
/*      */         for (int a : node.children)
/*      */           xx += ((FocusElementNode)this.table.data.get(Integer.valueOf(a))).x; 
/*      */         xx /= node.children.length;
/*      */         node.x = xx;
/*      */       } 
/*      */     } 
/*      */     if (this.selectedNode >= 0 && !this.table.data.containsKey(Integer.valueOf(this.selectedNode)))
/*      */       this.selectedNode = -1; 
/*      */   }
/*      */   
/*      */   private void resetNodes() {
/*      */     this.nodeID = 0;
/*      */     this.table.data.clear();
/*      */     ArrayList<Integer> r1 = addNodeAt(thaumcraft.api.casters.FocusMediumRoot.class, 0, false);
/*      */     this.selectedNode = ((Integer)r1.get(1)).intValue();
/*      */     calcNodeTreeLayout();
/*      */     gatherInfo(true);
/*      */   }
/*      */   
/*      */   private void calcScrollBounds() {
/*      */     this.sMinX = 0;
/*      */     this.sMinY = 0;
/*      */     this.sMaxX = 0;
/*      */     this.sMaxY = 0;
/*      */     for (FocusElementNode fn : this.table.data.values()) {
/*      */       if (fn.x < this.sMinX)
/*      */         this.sMinX = fn.x; 
/*      */       if (fn.y < this.sMinY)
/*      */         this.sMinY = fn.y; 
/*      */       if (fn.x > this.sMaxX)
/*      */         this.sMaxX = fn.x; 
/*      */       if (fn.y > this.sMaxY)
/*      */         this.sMaxY = fn.y; 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void gatherPartsList() {
/*      */     this.shownParts.clear();
/*      */     if (this.field_146297_k == null)
/*      */       return; 
/*      */     if (this.selectedNode >= 0 && this.table.data.containsKey(Integer.valueOf(this.selectedNode))) {
/*      */       this.partsStart = 0;
/*      */       ArrayList<String> pMed = new ArrayList<String>();
/*      */       ArrayList<String> pEff = new ArrayList<String>();
/*      */       ArrayList<String> pMod = new ArrayList<String>();
/*      */       ArrayList<String> excluded = new ArrayList<String>();
/*      */       boolean hasExlusive = false;
/*      */       boolean hasMedium = false;
/*      */       for (FocusElementNode fn : this.table.data.values()) {
/*      */         if (fn.node != null && fn.node instanceof FocusMedium) {
/*      */           hasMedium = !(fn.node instanceof thaumcraft.api.casters.FocusMediumRoot);
/*      */           if (((FocusMedium)fn.node).isExclusive()) {
/*      */             hasExlusive = true;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */         if (fn.node != null && fn.node.isExclusive())
/*      */           excluded.add(fn.node.getKey()); 
/*      */       } 
/*      */       FocusElementNode node = (FocusElementNode)this.table.data.get(Integer.valueOf(this.selectedNode));
/*      */       FocusElementNode parent = (FocusElementNode)this.table.data.get(Integer.valueOf(node.parent));
/*      */       if (parent != null && parent.node != null)
/*      */         for (String key : FocusEngine.elements.keySet()) {
/*      */           IFocusElement element = FocusEngine.getElement(key);
/*      */           if (!ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { element.getResearch() }))
/*      */             continue; 
/*      */           if (element.getKey().equals("thaumcraft.ROOT"))
/*      */             continue; 
/*      */           if (element instanceof FocusNode) {
/*      */             FocusNode fn = (FocusNode)element;
/*      */             if (!excluded.contains(fn.getKey()) && fn.mustBeSupplied() != null)
/*      */               for (FocusNode.EnumSupplyType type : fn.mustBeSupplied()) {
/*      */                 if (parent.node.canSupply(type)) {
/*      */                   switch (element.getType()) {
/*      */                     case EFFECT:
/*      */                       pEff.add(key);
/*      */                       break;
/*      */                     case MEDIUM:
/*      */                       if (!hasExlusive && (!((FocusMedium)element).isExclusive() || !hasMedium))
/*      */                         pMed.add(key); 
/*      */                       break;
/*      */                     case MOD:
/*      */                       pMod.add(key);
/*      */                       break;
/*      */                   } 
/*      */                   break;
/*      */                 } 
/*      */               }  
/*      */           } 
/*      */         }  
/*      */       Collections.sort(pMed);
/*      */       Collections.sort(pEff);
/*      */       Collections.sort(pMod);
/*      */       this.shownParts.addAll(pMed);
/*      */       this.shownParts.addAll(pEff);
/*      */       this.shownParts.addAll(pMod);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void gatherInfo(boolean sync) {
/*      */     this.field_146292_n.clear();
/*      */     this.field_146292_n.add(this.buttonConfirm);
/*      */     this.buttonConfirm.field_146128_h = this.field_147003_i + 242;
/*      */     this.buttonConfirm.field_146129_i = this.field_147009_r + 18;
/*      */     this.field_146292_n.add(new GuiHoverButton(this, 1, this.field_147003_i + 241, this.field_147009_r + 39, 32, 16, I18n.func_74838_a("wandtable.text5"), null, new ResourceLocation("thaumcraft", "textures/gui/complex.png")));
/*      */     this.field_146292_n.add(new GuiHoverButton(this, 2, this.field_147003_i + 241, this.field_147009_r + 53, 32, 16, I18n.func_74838_a("wandtable.text2"), null, new ResourceLocation("thaumcraft", "textures/gui/costxp.png")));
/*      */     this.field_146292_n.add(new GuiHoverButton(this, 3, this.field_147003_i + 241, this.field_147009_r + 67, 32, 16, I18n.func_74838_a("wandtable.text1"), null, new ResourceLocation("thaumcraft", "textures/gui/costvis.png")));
/*      */     FocusElementNode fn = (FocusElementNode)this.table.data.get(Integer.valueOf(this.selectedNode));
/*      */     if (fn != null && fn.node != null && !fn.node.getSettingList().isEmpty()) {
/*      */       int a = 0;
/*      */       for (String sk : fn.node.getSettingList()) {
/*      */         NodeSetting setting = fn.node.getSetting(sk);
/*      */         if (setting.getResearch() != null && !ThaumcraftCapabilities.knowsResearchStrict(this.field_146297_k.field_71439_g, new String[] { setting.getResearch() }))
/*      */           continue; 
/*      */         int x = this.field_147003_i + this.field_146999_f;
/*      */         int y = this.field_147009_r + this.field_147000_g + 3 - fn.node.getSettingList().size() * 26 + a * 26;
/*      */         int w = 32;
/*      */         if (setting.getType() instanceof NodeSetting.NodeSettingIntList)
/*      */           w = 72; 
/*      */         this.field_146292_n.add(new GuiFocusSettingSpinnerButton(10 + a, x, y, w, setting));
/*      */         a++;
/*      */       } 
/*      */     } 
/*      */     this.shownParts.clear();
/*      */     this.components = null;
/*      */     if (this.table.func_70301_a(false) == null || this.table.func_70301_a(0).func_190926_b())
/*      */       return; 
/*      */     HashMap<String, Integer> compCount = new HashMap<String, Integer>();
/*      */     this.totalComplexity = 0;
/*      */     this.maxComplexity = 0;
/*      */     if (this.field_147002_h.func_75139_a(0).func_75216_d()) {
/*      */       ItemStack is = this.field_147002_h.func_75139_a(0).func_75211_c();
/*      */       if (is != null && !is.func_190926_b() && is.func_77973_b() instanceof ItemFocus)
/*      */         this.maxComplexity = ((ItemFocus)is.func_77973_b()).getMaxComplexity(); 
/*      */     } 
/*      */     boolean emptyNodes = false;
/*      */     AspectList crystals = new AspectList();
/*      */     if (this.table.data != null && !this.table.data.isEmpty())
/*      */       for (FocusElementNode node : this.table.data.values()) {
/*      */         if (node.node != null) {
/*      */           int a = 0;
/*      */           if (compCount.containsKey(node.node.getKey()))
/*      */             a = ((Integer)compCount.get(node.node.getKey())).intValue(); 
/*      */           a++;
/*      */           node.complexityMultiplier = 0.5F * (a + 1);
/*      */           compCount.put(node.node.getKey(), Integer.valueOf(a));
/*      */           this.totalComplexity = (int)(this.totalComplexity + node.node.getComplexity() * node.complexityMultiplier);
/*      */           if (node.node.getAspect() != null)
/*      */             crystals.add(node.node.getAspect(), 1); 
/*      */           continue;
/*      */         } 
/*      */         emptyNodes = true;
/*      */       }  
/*      */     this.costCast = this.totalComplexity / 5.0F;
/*      */     this.costVis = this.totalComplexity * 10 + this.maxComplexity / 5;
/*      */     this.costXp = (int)Math.min(1L, Math.round(Math.sqrt(this.totalComplexity)));
/*      */     boolean validCrystals = false;
/*      */     if (crystals.getAspects().length > 0) {
/*      */       validCrystals = true;
/*      */       this.components = new ItemStack[crystals.getAspects().length];
/*      */       int r = 0;
/*      */       for (Aspect as : crystals.getAspects()) {
/*      */         this.components[r] = ThaumcraftApiHelper.makeCrystal(as, crystals.getAmount(as));
/*      */         r++;
/*      */       } 
/*      */       if (this.components.length >= 0) {
/*      */         boolean[] owns = new boolean[this.components.length];
/*      */         for (int a = 0; a < this.components.length; a++) {
/*      */           owns[a] = InventoryUtils.isPlayerCarryingAmount(this.field_146297_k.field_71439_g, this.components[a], false);
/*      */           if (!owns[a])
/*      */             validCrystals = false; 
/*      */         } 
/*      */       } 
/*      */       if (this.components != null && this.components.length > 0) {
/*      */         int i = 0;
/*      */         int q = 0;
/*      */         int z = 0;
/*      */         for (ItemStack stack : this.components) {
/*      */           this.field_146292_n.add(new GuiHoverButton(this, 11 + z, this.field_147003_i + 234 + i * 16, this.field_147009_r + 110 + 16 * q, 16, 16, stack.func_82833_r(), null, stack));
/*      */           i++;
/*      */           if (i > 4) {
/*      */             i = 0;
/*      */             q++;
/*      */           } 
/*      */           z++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     gatherPartsList();
/*      */     this.scrollbarParts = null;
/*      */     if (this.shownParts.size() > 6) {
/*      */       this.scrollbarParts = new GuiSliderTC(4, this.field_147003_i + 51, this.field_147009_r + 30, 8, 149, "logistics.scrollbar", 0.0F, (this.shownParts.size() - 6), 0.0F, true);
/*      */       this.field_146292_n.add(this.scrollbarParts);
/*      */     } 
/*      */     this.valid = (this.totalComplexity <= this.maxComplexity && !emptyNodes && validCrystals && this.table.xpCost <= this.field_146297_k.field_71439_g.field_71068_ca);
/*      */     calcScrollBounds();
/*      */     if (this.scrollY > (this.sMaxY - 3) * 32)
/*      */       this.scrollY = (this.sMaxY - 3) * 32; 
/*      */     if (this.scrollX > this.sMaxX * 24)
/*      */       this.scrollX = this.sMaxX * 24; 
/*      */     if (this.scrollX < this.sMinX * 24)
/*      */       this.scrollX = this.sMinX * 24; 
/*      */     this.scrollbarMainSide = null;
/*      */     this.scrollbarMainBottom = null;
/*      */     if (this.sMaxY * 32 > 130) {
/*      */       this.scrollbarMainSide = new GuiSliderTC(5, this.field_147003_i + 203, this.field_147009_r + 32, 8, 156, "logistics.scrollbar", 0.0F, ((this.sMaxY - 3) * 32), this.scrollY, true);
/*      */       this.field_146292_n.add(this.scrollbarMainSide);
/*      */     } else {
/*      */       this.scrollY = 0;
/*      */     } 
/*      */     if (this.sMinX * 24 < -70 || this.sMaxX * 24 > 70) {
/*      */       this.scrollbarMainBottom = new GuiSliderTC(6, this.field_147003_i + 64, this.field_147009_r + 195, 132, 8, "logistics.scrollbar", (this.sMinX * 24), (this.sMaxX * 24), this.scrollX, false);
/*      */       this.field_146292_n.add(this.scrollbarMainBottom);
/*      */     } else {
/*      */       this.scrollX = 0;
/*      */     } 
/*      */     if (this.table.focusName.isEmpty() && !this.table.func_70301_a(0).func_190926_b())
/*      */       this.table.focusName = this.table.func_70301_a(0).func_82833_r(); 
/*      */     if (this.nameField == null)
/*      */       setupNameField(); 
/*      */     this.nameField.func_146180_a(this.table.focusName);
/*      */     if (sync)
/*      */       PacketHandler.INSTANCE.sendToServer(new PacketFocusNodesToServer(this.table.func_174877_v(), this.table.data, this.table.focusName)); 
/*      */   }
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiFocalManipulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */