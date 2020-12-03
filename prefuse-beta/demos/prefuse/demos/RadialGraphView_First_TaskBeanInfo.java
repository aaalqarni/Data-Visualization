/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prefuse.demos;

import java.beans.*;

/**
 *
 * @author aaalqarni
 */
public class RadialGraphView_First_TaskBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( prefuse.demos.RadialGraphView_First_Task.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor

        // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor
    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_accessibleContext = 0;
    private static final int PROPERTY_actionMap = 1;
    private static final int PROPERTY_alignmentX = 2;
    private static final int PROPERTY_alignmentY = 3;
    private static final int PROPERTY_ancestorListeners = 4;
    private static final int PROPERTY_autoscrolls = 5;
    private static final int PROPERTY_background = 6;
    private static final int PROPERTY_backgroundSet = 7;
    private static final int PROPERTY_baselineResizeBehavior = 8;
    private static final int PROPERTY_border = 9;
    private static final int PROPERTY_bounds = 10;
    private static final int PROPERTY_colorModel = 11;
    private static final int PROPERTY_component = 12;
    private static final int PROPERTY_componentCount = 13;
    private static final int PROPERTY_componentListeners = 14;
    private static final int PROPERTY_componentOrientation = 15;
    private static final int PROPERTY_componentPopupMenu = 16;
    private static final int PROPERTY_components = 17;
    private static final int PROPERTY_containerListeners = 18;
    private static final int PROPERTY_cursor = 19;
    private static final int PROPERTY_cursorSet = 20;
    private static final int PROPERTY_customToolTip = 21;
    private static final int PROPERTY_damageRedraw = 22;
    private static final int PROPERTY_debugGraphicsOptions = 23;
    private static final int PROPERTY_displayable = 24;
    private static final int PROPERTY_displayX = 25;
    private static final int PROPERTY_displayY = 26;
    private static final int PROPERTY_doubleBuffered = 27;
    private static final int PROPERTY_dropTarget = 28;
    private static final int PROPERTY_enabled = 29;
    private static final int PROPERTY_focusable = 30;
    private static final int PROPERTY_focusCycleRoot = 31;
    private static final int PROPERTY_focusCycleRootAncestor = 32;
    private static final int PROPERTY_focusListeners = 33;
    private static final int PROPERTY_focusOwner = 34;
    private static final int PROPERTY_focusTraversable = 35;
    private static final int PROPERTY_focusTraversalKeys = 36;
    private static final int PROPERTY_focusTraversalKeysEnabled = 37;
    private static final int PROPERTY_focusTraversalPolicy = 38;
    private static final int PROPERTY_focusTraversalPolicyProvider = 39;
    private static final int PROPERTY_focusTraversalPolicySet = 40;
    private static final int PROPERTY_font = 41;
    private static final int PROPERTY_fontSet = 42;
    private static final int PROPERTY_foreground = 43;
    private static final int PROPERTY_foregroundSet = 44;
    private static final int PROPERTY_frameRate = 45;
    private static final int PROPERTY_graphics = 46;
    private static final int PROPERTY_graphicsConfiguration = 47;
    private static final int PROPERTY_height = 48;
    private static final int PROPERTY_hierarchyBoundsListeners = 49;
    private static final int PROPERTY_hierarchyListeners = 50;
    private static final int PROPERTY_highQuality = 51;
    private static final int PROPERTY_ignoreRepaint = 52;
    private static final int PROPERTY_inheritsPopupMenu = 53;
    private static final int PROPERTY_inputContext = 54;
    private static final int PROPERTY_inputMap = 55;
    private static final int PROPERTY_inputMethodListeners = 56;
    private static final int PROPERTY_inputMethodRequests = 57;
    private static final int PROPERTY_inputVerifier = 58;
    private static final int PROPERTY_insets = 59;
    private static final int PROPERTY_inverseTransform = 60;
    private static final int PROPERTY_itemBounds = 61;
    private static final int PROPERTY_itemSorter = 62;
    private static final int PROPERTY_keyListeners = 63;
    private static final int PROPERTY_layout = 64;
    private static final int PROPERTY_lightweight = 65;
    private static final int PROPERTY_locale = 66;
    private static final int PROPERTY_location = 67;
    private static final int PROPERTY_locationOnScreen = 68;
    private static final int PROPERTY_managingFocus = 69;
    private static final int PROPERTY_maximumSize = 70;
    private static final int PROPERTY_maximumSizeSet = 71;
    private static final int PROPERTY_minimumSize = 72;
    private static final int PROPERTY_minimumSizeSet = 73;
    private static final int PROPERTY_mouseListeners = 74;
    private static final int PROPERTY_mouseMotionListeners = 75;
    private static final int PROPERTY_mousePosition = 76;
    private static final int PROPERTY_mouseWheelListeners = 77;
    private static final int PROPERTY_name = 78;
    private static final int PROPERTY_nextFocusableComponent = 79;
    private static final int PROPERTY_offscreenBuffer = 80;
    private static final int PROPERTY_opaque = 81;
    private static final int PROPERTY_optimizedDrawingEnabled = 82;
    private static final int PROPERTY_paintingForPrint = 83;
    private static final int PROPERTY_paintingTile = 84;
    private static final int PROPERTY_parent = 85;
    private static final int PROPERTY_peer = 86;
    private static final int PROPERTY_predicate = 87;
    private static final int PROPERTY_preferredSize = 88;
    private static final int PROPERTY_preferredSizeSet = 89;
    private static final int PROPERTY_propertyChangeListeners = 90;
    private static final int PROPERTY_registeredKeyStrokes = 91;
    private static final int PROPERTY_requestFocusEnabled = 92;
    private static final int PROPERTY_rootPane = 93;
    private static final int PROPERTY_scale = 94;
    private static final int PROPERTY_showing = 95;
    private static final int PROPERTY_size = 96;
    private static final int PROPERTY_textEditor = 97;
    private static final int PROPERTY_toolkit = 98;
    private static final int PROPERTY_toolTipText = 99;
    private static final int PROPERTY_topLevelAncestor = 100;
    private static final int PROPERTY_tranformInProgress = 101;
    private static final int PROPERTY_transferHandler = 102;
    private static final int PROPERTY_transform = 103;
    private static final int PROPERTY_treeLock = 104;
    private static final int PROPERTY_UIClassID = 105;
    private static final int PROPERTY_valid = 106;
    private static final int PROPERTY_validateRoot = 107;
    private static final int PROPERTY_verifyInputWhenFocusTarget = 108;
    private static final int PROPERTY_vetoableChangeListeners = 109;
    private static final int PROPERTY_visible = 110;
    private static final int PROPERTY_visibleItemCount = 111;
    private static final int PROPERTY_visibleRect = 112;
    private static final int PROPERTY_visualization = 113;
    private static final int PROPERTY_width = 114;
    private static final int PROPERTY_x = 115;
    private static final int PROPERTY_y = 116;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[117];
    
        try {
            properties[PROPERTY_accessibleContext] = new PropertyDescriptor ( "accessibleContext", prefuse.demos.RadialGraphView_First_Task.class, "getAccessibleContext", null ); // NOI18N
            properties[PROPERTY_actionMap] = new PropertyDescriptor ( "actionMap", prefuse.demos.RadialGraphView_First_Task.class, "getActionMap", "setActionMap" ); // NOI18N
            properties[PROPERTY_alignmentX] = new PropertyDescriptor ( "alignmentX", prefuse.demos.RadialGraphView_First_Task.class, "getAlignmentX", "setAlignmentX" ); // NOI18N
            properties[PROPERTY_alignmentY] = new PropertyDescriptor ( "alignmentY", prefuse.demos.RadialGraphView_First_Task.class, "getAlignmentY", "setAlignmentY" ); // NOI18N
            properties[PROPERTY_ancestorListeners] = new PropertyDescriptor ( "ancestorListeners", prefuse.demos.RadialGraphView_First_Task.class, "getAncestorListeners", null ); // NOI18N
            properties[PROPERTY_autoscrolls] = new PropertyDescriptor ( "autoscrolls", prefuse.demos.RadialGraphView_First_Task.class, "getAutoscrolls", "setAutoscrolls" ); // NOI18N
            properties[PROPERTY_background] = new PropertyDescriptor ( "background", prefuse.demos.RadialGraphView_First_Task.class, "getBackground", "setBackground" ); // NOI18N
            properties[PROPERTY_backgroundSet] = new PropertyDescriptor ( "backgroundSet", prefuse.demos.RadialGraphView_First_Task.class, "isBackgroundSet", null ); // NOI18N
            properties[PROPERTY_baselineResizeBehavior] = new PropertyDescriptor ( "baselineResizeBehavior", prefuse.demos.RadialGraphView_First_Task.class, "getBaselineResizeBehavior", null ); // NOI18N
            properties[PROPERTY_border] = new PropertyDescriptor ( "border", prefuse.demos.RadialGraphView_First_Task.class, "getBorder", "setBorder" ); // NOI18N
            properties[PROPERTY_bounds] = new PropertyDescriptor ( "bounds", prefuse.demos.RadialGraphView_First_Task.class, "getBounds", "setBounds" ); // NOI18N
            properties[PROPERTY_colorModel] = new PropertyDescriptor ( "colorModel", prefuse.demos.RadialGraphView_First_Task.class, "getColorModel", null ); // NOI18N
            properties[PROPERTY_component] = new IndexedPropertyDescriptor ( "component", prefuse.demos.RadialGraphView_First_Task.class, null, null, "getComponent", null ); // NOI18N
            properties[PROPERTY_componentCount] = new PropertyDescriptor ( "componentCount", prefuse.demos.RadialGraphView_First_Task.class, "getComponentCount", null ); // NOI18N
            properties[PROPERTY_componentListeners] = new PropertyDescriptor ( "componentListeners", prefuse.demos.RadialGraphView_First_Task.class, "getComponentListeners", null ); // NOI18N
            properties[PROPERTY_componentOrientation] = new PropertyDescriptor ( "componentOrientation", prefuse.demos.RadialGraphView_First_Task.class, "getComponentOrientation", "setComponentOrientation" ); // NOI18N
            properties[PROPERTY_componentPopupMenu] = new PropertyDescriptor ( "componentPopupMenu", prefuse.demos.RadialGraphView_First_Task.class, "getComponentPopupMenu", "setComponentPopupMenu" ); // NOI18N
            properties[PROPERTY_components] = new PropertyDescriptor ( "components", prefuse.demos.RadialGraphView_First_Task.class, "getComponents", null ); // NOI18N
            properties[PROPERTY_containerListeners] = new PropertyDescriptor ( "containerListeners", prefuse.demos.RadialGraphView_First_Task.class, "getContainerListeners", null ); // NOI18N
            properties[PROPERTY_cursor] = new PropertyDescriptor ( "cursor", prefuse.demos.RadialGraphView_First_Task.class, "getCursor", "setCursor" ); // NOI18N
            properties[PROPERTY_cursorSet] = new PropertyDescriptor ( "cursorSet", prefuse.demos.RadialGraphView_First_Task.class, "isCursorSet", null ); // NOI18N
            properties[PROPERTY_customToolTip] = new PropertyDescriptor ( "customToolTip", prefuse.demos.RadialGraphView_First_Task.class, "getCustomToolTip", "setCustomToolTip" ); // NOI18N
            properties[PROPERTY_damageRedraw] = new PropertyDescriptor ( "damageRedraw", prefuse.demos.RadialGraphView_First_Task.class, "isDamageRedraw", "setDamageRedraw" ); // NOI18N
            properties[PROPERTY_debugGraphicsOptions] = new PropertyDescriptor ( "debugGraphicsOptions", prefuse.demos.RadialGraphView_First_Task.class, "getDebugGraphicsOptions", "setDebugGraphicsOptions" ); // NOI18N
            properties[PROPERTY_displayable] = new PropertyDescriptor ( "displayable", prefuse.demos.RadialGraphView_First_Task.class, "isDisplayable", null ); // NOI18N
            properties[PROPERTY_displayX] = new PropertyDescriptor ( "displayX", prefuse.demos.RadialGraphView_First_Task.class, "getDisplayX", null ); // NOI18N
            properties[PROPERTY_displayY] = new PropertyDescriptor ( "displayY", prefuse.demos.RadialGraphView_First_Task.class, "getDisplayY", null ); // NOI18N
            properties[PROPERTY_doubleBuffered] = new PropertyDescriptor ( "doubleBuffered", prefuse.demos.RadialGraphView_First_Task.class, "isDoubleBuffered", "setDoubleBuffered" ); // NOI18N
            properties[PROPERTY_dropTarget] = new PropertyDescriptor ( "dropTarget", prefuse.demos.RadialGraphView_First_Task.class, "getDropTarget", "setDropTarget" ); // NOI18N
            properties[PROPERTY_enabled] = new PropertyDescriptor ( "enabled", prefuse.demos.RadialGraphView_First_Task.class, "isEnabled", "setEnabled" ); // NOI18N
            properties[PROPERTY_focusable] = new PropertyDescriptor ( "focusable", prefuse.demos.RadialGraphView_First_Task.class, "isFocusable", "setFocusable" ); // NOI18N
            properties[PROPERTY_focusCycleRoot] = new PropertyDescriptor ( "focusCycleRoot", prefuse.demos.RadialGraphView_First_Task.class, "isFocusCycleRoot", "setFocusCycleRoot" ); // NOI18N
            properties[PROPERTY_focusCycleRootAncestor] = new PropertyDescriptor ( "focusCycleRootAncestor", prefuse.demos.RadialGraphView_First_Task.class, "getFocusCycleRootAncestor", null ); // NOI18N
            properties[PROPERTY_focusListeners] = new PropertyDescriptor ( "focusListeners", prefuse.demos.RadialGraphView_First_Task.class, "getFocusListeners", null ); // NOI18N
            properties[PROPERTY_focusOwner] = new PropertyDescriptor ( "focusOwner", prefuse.demos.RadialGraphView_First_Task.class, "isFocusOwner", null ); // NOI18N
            properties[PROPERTY_focusTraversable] = new PropertyDescriptor ( "focusTraversable", prefuse.demos.RadialGraphView_First_Task.class, "isFocusTraversable", null ); // NOI18N
            properties[PROPERTY_focusTraversalKeys] = new IndexedPropertyDescriptor ( "focusTraversalKeys", prefuse.demos.RadialGraphView_First_Task.class, null, null, null, "setFocusTraversalKeys" ); // NOI18N
            properties[PROPERTY_focusTraversalKeysEnabled] = new PropertyDescriptor ( "focusTraversalKeysEnabled", prefuse.demos.RadialGraphView_First_Task.class, "getFocusTraversalKeysEnabled", "setFocusTraversalKeysEnabled" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicy] = new PropertyDescriptor ( "focusTraversalPolicy", prefuse.demos.RadialGraphView_First_Task.class, "getFocusTraversalPolicy", "setFocusTraversalPolicy" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicyProvider] = new PropertyDescriptor ( "focusTraversalPolicyProvider", prefuse.demos.RadialGraphView_First_Task.class, "isFocusTraversalPolicyProvider", "setFocusTraversalPolicyProvider" ); // NOI18N
            properties[PROPERTY_focusTraversalPolicySet] = new PropertyDescriptor ( "focusTraversalPolicySet", prefuse.demos.RadialGraphView_First_Task.class, "isFocusTraversalPolicySet", null ); // NOI18N
            properties[PROPERTY_font] = new PropertyDescriptor ( "font", prefuse.demos.RadialGraphView_First_Task.class, "getFont", "setFont" ); // NOI18N
            properties[PROPERTY_fontSet] = new PropertyDescriptor ( "fontSet", prefuse.demos.RadialGraphView_First_Task.class, "isFontSet", null ); // NOI18N
            properties[PROPERTY_foreground] = new PropertyDescriptor ( "foreground", prefuse.demos.RadialGraphView_First_Task.class, "getForeground", "setForeground" ); // NOI18N
            properties[PROPERTY_foregroundSet] = new PropertyDescriptor ( "foregroundSet", prefuse.demos.RadialGraphView_First_Task.class, "isForegroundSet", null ); // NOI18N
            properties[PROPERTY_frameRate] = new PropertyDescriptor ( "frameRate", prefuse.demos.RadialGraphView_First_Task.class, "getFrameRate", null ); // NOI18N
            properties[PROPERTY_graphics] = new PropertyDescriptor ( "graphics", prefuse.demos.RadialGraphView_First_Task.class, "getGraphics", null ); // NOI18N
            properties[PROPERTY_graphicsConfiguration] = new PropertyDescriptor ( "graphicsConfiguration", prefuse.demos.RadialGraphView_First_Task.class, "getGraphicsConfiguration", null ); // NOI18N
            properties[PROPERTY_height] = new PropertyDescriptor ( "height", prefuse.demos.RadialGraphView_First_Task.class, "getHeight", null ); // NOI18N
            properties[PROPERTY_hierarchyBoundsListeners] = new PropertyDescriptor ( "hierarchyBoundsListeners", prefuse.demos.RadialGraphView_First_Task.class, "getHierarchyBoundsListeners", null ); // NOI18N
            properties[PROPERTY_hierarchyListeners] = new PropertyDescriptor ( "hierarchyListeners", prefuse.demos.RadialGraphView_First_Task.class, "getHierarchyListeners", null ); // NOI18N
            properties[PROPERTY_highQuality] = new PropertyDescriptor ( "highQuality", prefuse.demos.RadialGraphView_First_Task.class, "isHighQuality", "setHighQuality" ); // NOI18N
            properties[PROPERTY_ignoreRepaint] = new PropertyDescriptor ( "ignoreRepaint", prefuse.demos.RadialGraphView_First_Task.class, "getIgnoreRepaint", "setIgnoreRepaint" ); // NOI18N
            properties[PROPERTY_inheritsPopupMenu] = new PropertyDescriptor ( "inheritsPopupMenu", prefuse.demos.RadialGraphView_First_Task.class, "getInheritsPopupMenu", "setInheritsPopupMenu" ); // NOI18N
            properties[PROPERTY_inputContext] = new PropertyDescriptor ( "inputContext", prefuse.demos.RadialGraphView_First_Task.class, "getInputContext", null ); // NOI18N
            properties[PROPERTY_inputMap] = new PropertyDescriptor ( "inputMap", prefuse.demos.RadialGraphView_First_Task.class, "getInputMap", null ); // NOI18N
            properties[PROPERTY_inputMethodListeners] = new PropertyDescriptor ( "inputMethodListeners", prefuse.demos.RadialGraphView_First_Task.class, "getInputMethodListeners", null ); // NOI18N
            properties[PROPERTY_inputMethodRequests] = new PropertyDescriptor ( "inputMethodRequests", prefuse.demos.RadialGraphView_First_Task.class, "getInputMethodRequests", null ); // NOI18N
            properties[PROPERTY_inputVerifier] = new PropertyDescriptor ( "inputVerifier", prefuse.demos.RadialGraphView_First_Task.class, "getInputVerifier", "setInputVerifier" ); // NOI18N
            properties[PROPERTY_insets] = new PropertyDescriptor ( "insets", prefuse.demos.RadialGraphView_First_Task.class, "getInsets", null ); // NOI18N
            properties[PROPERTY_inverseTransform] = new PropertyDescriptor ( "inverseTransform", prefuse.demos.RadialGraphView_First_Task.class, "getInverseTransform", null ); // NOI18N
            properties[PROPERTY_itemBounds] = new PropertyDescriptor ( "itemBounds", prefuse.demos.RadialGraphView_First_Task.class, "getItemBounds", null ); // NOI18N
            properties[PROPERTY_itemSorter] = new PropertyDescriptor ( "itemSorter", prefuse.demos.RadialGraphView_First_Task.class, "getItemSorter", "setItemSorter" ); // NOI18N
            properties[PROPERTY_keyListeners] = new PropertyDescriptor ( "keyListeners", prefuse.demos.RadialGraphView_First_Task.class, "getKeyListeners", null ); // NOI18N
            properties[PROPERTY_layout] = new PropertyDescriptor ( "layout", prefuse.demos.RadialGraphView_First_Task.class, "getLayout", "setLayout" ); // NOI18N
            properties[PROPERTY_lightweight] = new PropertyDescriptor ( "lightweight", prefuse.demos.RadialGraphView_First_Task.class, "isLightweight", null ); // NOI18N
            properties[PROPERTY_locale] = new PropertyDescriptor ( "locale", prefuse.demos.RadialGraphView_First_Task.class, "getLocale", "setLocale" ); // NOI18N
            properties[PROPERTY_location] = new PropertyDescriptor ( "location", prefuse.demos.RadialGraphView_First_Task.class, "getLocation", "setLocation" ); // NOI18N
            properties[PROPERTY_locationOnScreen] = new PropertyDescriptor ( "locationOnScreen", prefuse.demos.RadialGraphView_First_Task.class, "getLocationOnScreen", null ); // NOI18N
            properties[PROPERTY_managingFocus] = new PropertyDescriptor ( "managingFocus", prefuse.demos.RadialGraphView_First_Task.class, "isManagingFocus", null ); // NOI18N
            properties[PROPERTY_maximumSize] = new PropertyDescriptor ( "maximumSize", prefuse.demos.RadialGraphView_First_Task.class, "getMaximumSize", "setMaximumSize" ); // NOI18N
            properties[PROPERTY_maximumSizeSet] = new PropertyDescriptor ( "maximumSizeSet", prefuse.demos.RadialGraphView_First_Task.class, "isMaximumSizeSet", null ); // NOI18N
            properties[PROPERTY_minimumSize] = new PropertyDescriptor ( "minimumSize", prefuse.demos.RadialGraphView_First_Task.class, "getMinimumSize", "setMinimumSize" ); // NOI18N
            properties[PROPERTY_minimumSizeSet] = new PropertyDescriptor ( "minimumSizeSet", prefuse.demos.RadialGraphView_First_Task.class, "isMinimumSizeSet", null ); // NOI18N
            properties[PROPERTY_mouseListeners] = new PropertyDescriptor ( "mouseListeners", prefuse.demos.RadialGraphView_First_Task.class, "getMouseListeners", null ); // NOI18N
            properties[PROPERTY_mouseMotionListeners] = new PropertyDescriptor ( "mouseMotionListeners", prefuse.demos.RadialGraphView_First_Task.class, "getMouseMotionListeners", null ); // NOI18N
            properties[PROPERTY_mousePosition] = new PropertyDescriptor ( "mousePosition", prefuse.demos.RadialGraphView_First_Task.class, "getMousePosition", null ); // NOI18N
            properties[PROPERTY_mouseWheelListeners] = new PropertyDescriptor ( "mouseWheelListeners", prefuse.demos.RadialGraphView_First_Task.class, "getMouseWheelListeners", null ); // NOI18N
            properties[PROPERTY_name] = new PropertyDescriptor ( "name", prefuse.demos.RadialGraphView_First_Task.class, "getName", "setName" ); // NOI18N
            properties[PROPERTY_nextFocusableComponent] = new PropertyDescriptor ( "nextFocusableComponent", prefuse.demos.RadialGraphView_First_Task.class, "getNextFocusableComponent", "setNextFocusableComponent" ); // NOI18N
            properties[PROPERTY_offscreenBuffer] = new PropertyDescriptor ( "offscreenBuffer", prefuse.demos.RadialGraphView_First_Task.class, "getOffscreenBuffer", null ); // NOI18N
            properties[PROPERTY_opaque] = new PropertyDescriptor ( "opaque", prefuse.demos.RadialGraphView_First_Task.class, "isOpaque", "setOpaque" ); // NOI18N
            properties[PROPERTY_optimizedDrawingEnabled] = new PropertyDescriptor ( "optimizedDrawingEnabled", prefuse.demos.RadialGraphView_First_Task.class, "isOptimizedDrawingEnabled", null ); // NOI18N
            properties[PROPERTY_paintingForPrint] = new PropertyDescriptor ( "paintingForPrint", prefuse.demos.RadialGraphView_First_Task.class, "isPaintingForPrint", null ); // NOI18N
            properties[PROPERTY_paintingTile] = new PropertyDescriptor ( "paintingTile", prefuse.demos.RadialGraphView_First_Task.class, "isPaintingTile", null ); // NOI18N
            properties[PROPERTY_parent] = new PropertyDescriptor ( "parent", prefuse.demos.RadialGraphView_First_Task.class, "getParent", null ); // NOI18N
            properties[PROPERTY_peer] = new PropertyDescriptor ( "peer", prefuse.demos.RadialGraphView_First_Task.class, "getPeer", null ); // NOI18N
            properties[PROPERTY_predicate] = new PropertyDescriptor ( "predicate", prefuse.demos.RadialGraphView_First_Task.class, null, "setPredicate" ); // NOI18N
            properties[PROPERTY_preferredSize] = new PropertyDescriptor ( "preferredSize", prefuse.demos.RadialGraphView_First_Task.class, "getPreferredSize", "setPreferredSize" ); // NOI18N
            properties[PROPERTY_preferredSizeSet] = new PropertyDescriptor ( "preferredSizeSet", prefuse.demos.RadialGraphView_First_Task.class, "isPreferredSizeSet", null ); // NOI18N
            properties[PROPERTY_propertyChangeListeners] = new PropertyDescriptor ( "propertyChangeListeners", prefuse.demos.RadialGraphView_First_Task.class, "getPropertyChangeListeners", null ); // NOI18N
            properties[PROPERTY_registeredKeyStrokes] = new PropertyDescriptor ( "registeredKeyStrokes", prefuse.demos.RadialGraphView_First_Task.class, "getRegisteredKeyStrokes", null ); // NOI18N
            properties[PROPERTY_requestFocusEnabled] = new PropertyDescriptor ( "requestFocusEnabled", prefuse.demos.RadialGraphView_First_Task.class, "isRequestFocusEnabled", "setRequestFocusEnabled" ); // NOI18N
            properties[PROPERTY_rootPane] = new PropertyDescriptor ( "rootPane", prefuse.demos.RadialGraphView_First_Task.class, "getRootPane", null ); // NOI18N
            properties[PROPERTY_scale] = new PropertyDescriptor ( "scale", prefuse.demos.RadialGraphView_First_Task.class, "getScale", null ); // NOI18N
            properties[PROPERTY_showing] = new PropertyDescriptor ( "showing", prefuse.demos.RadialGraphView_First_Task.class, "isShowing", null ); // NOI18N
            properties[PROPERTY_size] = new PropertyDescriptor ( "size", prefuse.demos.RadialGraphView_First_Task.class, "getSize", "setSize" ); // NOI18N
            properties[PROPERTY_textEditor] = new PropertyDescriptor ( "textEditor", prefuse.demos.RadialGraphView_First_Task.class, "getTextEditor", "setTextEditor" ); // NOI18N
            properties[PROPERTY_toolkit] = new PropertyDescriptor ( "toolkit", prefuse.demos.RadialGraphView_First_Task.class, "getToolkit", null ); // NOI18N
            properties[PROPERTY_toolTipText] = new PropertyDescriptor ( "toolTipText", prefuse.demos.RadialGraphView_First_Task.class, "getToolTipText", "setToolTipText" ); // NOI18N
            properties[PROPERTY_topLevelAncestor] = new PropertyDescriptor ( "topLevelAncestor", prefuse.demos.RadialGraphView_First_Task.class, "getTopLevelAncestor", null ); // NOI18N
            properties[PROPERTY_tranformInProgress] = new PropertyDescriptor ( "tranformInProgress", prefuse.demos.RadialGraphView_First_Task.class, "isTranformInProgress", null ); // NOI18N
            properties[PROPERTY_transferHandler] = new PropertyDescriptor ( "transferHandler", prefuse.demos.RadialGraphView_First_Task.class, "getTransferHandler", "setTransferHandler" ); // NOI18N
            properties[PROPERTY_transform] = new PropertyDescriptor ( "transform", prefuse.demos.RadialGraphView_First_Task.class, "getTransform", "setTransform" ); // NOI18N
            properties[PROPERTY_treeLock] = new PropertyDescriptor ( "treeLock", prefuse.demos.RadialGraphView_First_Task.class, "getTreeLock", null ); // NOI18N
            properties[PROPERTY_UIClassID] = new PropertyDescriptor ( "UIClassID", prefuse.demos.RadialGraphView_First_Task.class, "getUIClassID", null ); // NOI18N
            properties[PROPERTY_valid] = new PropertyDescriptor ( "valid", prefuse.demos.RadialGraphView_First_Task.class, "isValid", null ); // NOI18N
            properties[PROPERTY_validateRoot] = new PropertyDescriptor ( "validateRoot", prefuse.demos.RadialGraphView_First_Task.class, "isValidateRoot", null ); // NOI18N
            properties[PROPERTY_verifyInputWhenFocusTarget] = new PropertyDescriptor ( "verifyInputWhenFocusTarget", prefuse.demos.RadialGraphView_First_Task.class, "getVerifyInputWhenFocusTarget", "setVerifyInputWhenFocusTarget" ); // NOI18N
            properties[PROPERTY_vetoableChangeListeners] = new PropertyDescriptor ( "vetoableChangeListeners", prefuse.demos.RadialGraphView_First_Task.class, "getVetoableChangeListeners", null ); // NOI18N
            properties[PROPERTY_visible] = new PropertyDescriptor ( "visible", prefuse.demos.RadialGraphView_First_Task.class, "isVisible", "setVisible" ); // NOI18N
            properties[PROPERTY_visibleItemCount] = new PropertyDescriptor ( "visibleItemCount", prefuse.demos.RadialGraphView_First_Task.class, "getVisibleItemCount", null ); // NOI18N
            properties[PROPERTY_visibleRect] = new PropertyDescriptor ( "visibleRect", prefuse.demos.RadialGraphView_First_Task.class, "getVisibleRect", null ); // NOI18N
            properties[PROPERTY_visualization] = new PropertyDescriptor ( "visualization", prefuse.demos.RadialGraphView_First_Task.class, "getVisualization", "setVisualization" ); // NOI18N
            properties[PROPERTY_width] = new PropertyDescriptor ( "width", prefuse.demos.RadialGraphView_First_Task.class, "getWidth", null ); // NOI18N
            properties[PROPERTY_x] = new PropertyDescriptor ( "x", prefuse.demos.RadialGraphView_First_Task.class, "getX", null ); // NOI18N
            properties[PROPERTY_y] = new PropertyDescriptor ( "y", prefuse.demos.RadialGraphView_First_Task.class, "getY", null ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties

        // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties
    // EventSet identifiers//GEN-FIRST:Events
    private static final int EVENT_ancestorListener = 0;
    private static final int EVENT_componentListener = 1;
    private static final int EVENT_containerListener = 2;
    private static final int EVENT_focusListener = 3;
    private static final int EVENT_hierarchyBoundsListener = 4;
    private static final int EVENT_hierarchyListener = 5;
    private static final int EVENT_inputMethodListener = 6;
    private static final int EVENT_keyListener = 7;
    private static final int EVENT_mouseListener = 8;
    private static final int EVENT_mouseMotionListener = 9;
    private static final int EVENT_mouseWheelListener = 10;
    private static final int EVENT_paintListener = 11;
    private static final int EVENT_propertyChangeListener = 12;
    private static final int EVENT_vetoableChangeListener = 13;

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[14];
    
        try {
            eventSets[EVENT_ancestorListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "ancestorListener", javax.swing.event.AncestorListener.class, new String[] {"ancestorAdded", "ancestorRemoved", "ancestorMoved"}, "addAncestorListener", "removeAncestorListener" ); // NOI18N
            eventSets[EVENT_componentListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "componentListener", java.awt.event.ComponentListener.class, new String[] {"componentResized", "componentMoved", "componentShown", "componentHidden"}, "addComponentListener", "removeComponentListener" ); // NOI18N
            eventSets[EVENT_containerListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "containerListener", java.awt.event.ContainerListener.class, new String[] {"componentAdded", "componentRemoved"}, "addContainerListener", "removeContainerListener" ); // NOI18N
            eventSets[EVENT_focusListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "focusListener", java.awt.event.FocusListener.class, new String[] {"focusGained", "focusLost"}, "addFocusListener", "removeFocusListener" ); // NOI18N
            eventSets[EVENT_hierarchyBoundsListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "hierarchyBoundsListener", java.awt.event.HierarchyBoundsListener.class, new String[] {"ancestorMoved", "ancestorResized"}, "addHierarchyBoundsListener", "removeHierarchyBoundsListener" ); // NOI18N
            eventSets[EVENT_hierarchyListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "hierarchyListener", java.awt.event.HierarchyListener.class, new String[] {"hierarchyChanged"}, "addHierarchyListener", "removeHierarchyListener" ); // NOI18N
            eventSets[EVENT_inputMethodListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "inputMethodListener", java.awt.event.InputMethodListener.class, new String[] {"inputMethodTextChanged", "caretPositionChanged"}, "addInputMethodListener", "removeInputMethodListener" ); // NOI18N
            eventSets[EVENT_keyListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "keyListener", java.awt.event.KeyListener.class, new String[] {"keyTyped", "keyPressed", "keyReleased"}, "addKeyListener", "removeKeyListener" ); // NOI18N
            eventSets[EVENT_mouseListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "mouseListener", java.awt.event.MouseListener.class, new String[] {"mouseClicked", "mousePressed", "mouseReleased", "mouseEntered", "mouseExited"}, "addMouseListener", "removeMouseListener" ); // NOI18N
            eventSets[EVENT_mouseMotionListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "mouseMotionListener", java.awt.event.MouseMotionListener.class, new String[] {"mouseDragged", "mouseMoved"}, "addMouseMotionListener", "removeMouseMotionListener" ); // NOI18N
            eventSets[EVENT_mouseWheelListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "mouseWheelListener", java.awt.event.MouseWheelListener.class, new String[] {"mouseWheelMoved"}, "addMouseWheelListener", "removeMouseWheelListener" ); // NOI18N
            eventSets[EVENT_paintListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "paintListener", prefuse.util.display.PaintListener.class, new String[] {"prePaint", "postPaint"}, "addPaintListener", "removePaintListener" ); // NOI18N
            eventSets[EVENT_propertyChangeListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "propertyChangeListener", java.beans.PropertyChangeListener.class, new String[] {"propertyChange"}, "addPropertyChangeListener", "removePropertyChangeListener" ); // NOI18N
            eventSets[EVENT_vetoableChangeListener] = new EventSetDescriptor ( prefuse.demos.RadialGraphView_First_Task.class, "vetoableChangeListener", java.beans.VetoableChangeListener.class, new String[] {"vetoableChange"}, "addVetoableChangeListener", "removeVetoableChangeListener" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Events

        // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events
    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_action0 = 0;
    private static final int METHOD_add1 = 1;
    private static final int METHOD_add2 = 2;
    private static final int METHOD_add3 = 3;
    private static final int METHOD_add4 = 4;
    private static final int METHOD_add5 = 5;
    private static final int METHOD_add6 = 6;
    private static final int METHOD_addControlListener7 = 7;
    private static final int METHOD_addItemBoundsListener8 = 8;
    private static final int METHOD_addNotify9 = 9;
    private static final int METHOD_addPropertyChangeListener10 = 10;
    private static final int METHOD_animatePan11 = 11;
    private static final int METHOD_animatePanAbs12 = 12;
    private static final int METHOD_animatePanAndZoomTo13 = 13;
    private static final int METHOD_animatePanAndZoomToAbs14 = 14;
    private static final int METHOD_animatePanTo15 = 15;
    private static final int METHOD_animatePanToAbs16 = 16;
    private static final int METHOD_animateZoom17 = 17;
    private static final int METHOD_animateZoomAbs18 = 18;
    private static final int METHOD_applyComponentOrientation19 = 19;
    private static final int METHOD_areFocusTraversalKeysSet20 = 20;
    private static final int METHOD_bounds21 = 21;
    private static final int METHOD_checkImage22 = 22;
    private static final int METHOD_checkImage23 = 23;
    private static final int METHOD_clearDamage24 = 24;
    private static final int METHOD_computeVisibleRect25 = 25;
    private static final int METHOD_contains26 = 26;
    private static final int METHOD_contains27 = 27;
    private static final int METHOD_countComponents28 = 28;
    private static final int METHOD_createImage29 = 29;
    private static final int METHOD_createImage30 = 30;
    private static final int METHOD_createToolTip31 = 31;
    private static final int METHOD_createVolatileImage32 = 32;
    private static final int METHOD_createVolatileImage33 = 33;
    private static final int METHOD_damageReport34 = 34;
    private static final int METHOD_damageReport35 = 35;
    private static final int METHOD_deliverEvent36 = 36;
    private static final int METHOD_demo37 = 37;
    private static final int METHOD_demo38 = 38;
    private static final int METHOD_demo39 = 39;
    private static final int METHOD_disable40 = 40;
    private static final int METHOD_dispatchEvent41 = 41;
    private static final int METHOD_doLayout42 = 42;
    private static final int METHOD_editText43 = 43;
    private static final int METHOD_editText44 = 44;
    private static final int METHOD_editText45 = 45;
    private static final int METHOD_enable46 = 46;
    private static final int METHOD_enable47 = 47;
    private static final int METHOD_enableInputMethods48 = 48;
    private static final int METHOD_findComponentAt49 = 49;
    private static final int METHOD_findComponentAt50 = 50;
    private static final int METHOD_findItem51 = 51;
    private static final int METHOD_firePropertyChange52 = 52;
    private static final int METHOD_firePropertyChange53 = 53;
    private static final int METHOD_firePropertyChange54 = 54;
    private static final int METHOD_firePropertyChange55 = 55;
    private static final int METHOD_firePropertyChange56 = 56;
    private static final int METHOD_firePropertyChange57 = 57;
    private static final int METHOD_firePropertyChange58 = 58;
    private static final int METHOD_firePropertyChange59 = 59;
    private static final int METHOD_getAbsoluteCoordinate60 = 60;
    private static final int METHOD_getActionForKeyStroke61 = 61;
    private static final int METHOD_getBaseline62 = 62;
    private static final int METHOD_getBounds63 = 63;
    private static final int METHOD_getClientProperty64 = 64;
    private static final int METHOD_getComponentAt65 = 65;
    private static final int METHOD_getComponentAt66 = 66;
    private static final int METHOD_getComponentZOrder67 = 67;
    private static final int METHOD_getConditionForKeyStroke68 = 68;
    private static final int METHOD_getDefaultLocale69 = 69;
    private static final int METHOD_getFocusTraversalKeys70 = 70;
    private static final int METHOD_getFontMetrics71 = 71;
    private static final int METHOD_getInsets72 = 72;
    private static final int METHOD_getItemBounds73 = 73;
    private static final int METHOD_getListeners74 = 74;
    private static final int METHOD_getLocation75 = 75;
    private static final int METHOD_getMousePosition76 = 76;
    private static final int METHOD_getPopupLocation77 = 77;
    private static final int METHOD_getPredicate78 = 78;
    private static final int METHOD_getPropertyChangeListeners79 = 79;
    private static final int METHOD_getSize80 = 80;
    private static final int METHOD_getToolTipLocation81 = 81;
    private static final int METHOD_getToolTipText82 = 82;
    private static final int METHOD_gotFocus83 = 83;
    private static final int METHOD_grabFocus84 = 84;
    private static final int METHOD_handleEvent85 = 85;
    private static final int METHOD_hasFocus86 = 86;
    private static final int METHOD_hide87 = 87;
    private static final int METHOD_imageUpdate88 = 88;
    private static final int METHOD_insets89 = 89;
    private static final int METHOD_inside90 = 90;
    private static final int METHOD_invalidate91 = 91;
    private static final int METHOD_isAncestorOf92 = 92;
    private static final int METHOD_isFocusCycleRoot93 = 93;
    private static final int METHOD_isLightweightComponent94 = 94;
    private static final int METHOD_keyDown95 = 95;
    private static final int METHOD_keyUp96 = 96;
    private static final int METHOD_layout97 = 97;
    private static final int METHOD_list98 = 98;
    private static final int METHOD_list99 = 99;
    private static final int METHOD_list100 = 100;
    private static final int METHOD_list101 = 101;
    private static final int METHOD_list102 = 102;
    private static final int METHOD_locate103 = 103;
    private static final int METHOD_location104 = 104;
    private static final int METHOD_lostFocus105 = 105;
    private static final int METHOD_main106 = 106;
    private static final int METHOD_minimumSize107 = 107;
    private static final int METHOD_mouseDown108 = 108;
    private static final int METHOD_mouseDrag109 = 109;
    private static final int METHOD_mouseEnter110 = 110;
    private static final int METHOD_mouseExit111 = 111;
    private static final int METHOD_mouseMove112 = 112;
    private static final int METHOD_mouseUp113 = 113;
    private static final int METHOD_move114 = 114;
    private static final int METHOD_nextFocus115 = 115;
    private static final int METHOD_paint116 = 116;
    private static final int METHOD_paintAll117 = 117;
    private static final int METHOD_paintComponent118 = 118;
    private static final int METHOD_paintComponents119 = 119;
    private static final int METHOD_paintDisplay120 = 120;
    private static final int METHOD_paintImmediately121 = 121;
    private static final int METHOD_paintImmediately122 = 122;
    private static final int METHOD_pan123 = 123;
    private static final int METHOD_panAbs124 = 124;
    private static final int METHOD_panTo125 = 125;
    private static final int METHOD_panToAbs126 = 126;
    private static final int METHOD_postEvent127 = 127;
    private static final int METHOD_preferredSize128 = 128;
    private static final int METHOD_prepareImage129 = 129;
    private static final int METHOD_prepareImage130 = 130;
    private static final int METHOD_print131 = 131;
    private static final int METHOD_printAll132 = 132;
    private static final int METHOD_printComponents133 = 133;
    private static final int METHOD_putClientProperty134 = 134;
    private static final int METHOD_registerKeyboardAction135 = 135;
    private static final int METHOD_registerKeyboardAction136 = 136;
    private static final int METHOD_remove137 = 137;
    private static final int METHOD_remove138 = 138;
    private static final int METHOD_remove139 = 139;
    private static final int METHOD_removeAll140 = 140;
    private static final int METHOD_removeControlListener141 = 141;
    private static final int METHOD_removeItemBoundsListener142 = 142;
    private static final int METHOD_removeNotify143 = 143;
    private static final int METHOD_removePropertyChangeListener144 = 144;
    private static final int METHOD_renderImmediate145 = 145;
    private static final int METHOD_repaint146 = 146;
    private static final int METHOD_repaint147 = 147;
    private static final int METHOD_repaint148 = 148;
    private static final int METHOD_repaint149 = 149;
    private static final int METHOD_repaint150 = 150;
    private static final int METHOD_repaintImmediate151 = 151;
    private static final int METHOD_requestDefaultFocus152 = 152;
    private static final int METHOD_requestFocus153 = 153;
    private static final int METHOD_requestFocus154 = 154;
    private static final int METHOD_requestFocusInWindow155 = 155;
    private static final int METHOD_reset156 = 156;
    private static final int METHOD_resetKeyboardActions157 = 157;
    private static final int METHOD_reshape158 = 158;
    private static final int METHOD_resize159 = 159;
    private static final int METHOD_resize160 = 160;
    private static final int METHOD_revalidate161 = 161;
    private static final int METHOD_rotate162 = 162;
    private static final int METHOD_rotateAbs163 = 163;
    private static final int METHOD_saveImage164 = 164;
    private static final int METHOD_scrollRectToVisible165 = 165;
    private static final int METHOD_setBackgroundImage166 = 166;
    private static final int METHOD_setBackgroundImage167 = 167;
    private static final int METHOD_setBounds168 = 168;
    private static final int METHOD_setComponentZOrder169 = 169;
    private static final int METHOD_setDefaultLocale170 = 170;
    private static final int METHOD_setPredicate171 = 171;
    private static final int METHOD_show172 = 172;
    private static final int METHOD_show173 = 173;
    private static final int METHOD_size174 = 174;
    private static final int METHOD_stopEditing175 = 175;
    private static final int METHOD_toString176 = 176;
    private static final int METHOD_transferFocus177 = 177;
    private static final int METHOD_transferFocusBackward178 = 178;
    private static final int METHOD_transferFocusDownCycle179 = 179;
    private static final int METHOD_transferFocusUpCycle180 = 180;
    private static final int METHOD_unregisterKeyboardAction181 = 181;
    private static final int METHOD_update182 = 182;
    private static final int METHOD_updateUI183 = 183;
    private static final int METHOD_validate184 = 184;
    private static final int METHOD_zoom185 = 185;
    private static final int METHOD_zoomAbs186 = 186;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[187];
    
        try {
            methods[METHOD_action0] = new MethodDescriptor(java.awt.Component.class.getMethod("action", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_action0].setDisplayName ( "" );
            methods[METHOD_add1] = new MethodDescriptor(java.awt.Component.class.getMethod("add", new Class[] {java.awt.PopupMenu.class})); // NOI18N
            methods[METHOD_add1].setDisplayName ( "" );
            methods[METHOD_add2] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_add2].setDisplayName ( "" );
            methods[METHOD_add3] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.lang.String.class, java.awt.Component.class})); // NOI18N
            methods[METHOD_add3].setDisplayName ( "" );
            methods[METHOD_add4] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, int.class})); // NOI18N
            methods[METHOD_add4].setDisplayName ( "" );
            methods[METHOD_add5] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_add5].setDisplayName ( "" );
            methods[METHOD_add6] = new MethodDescriptor(java.awt.Container.class.getMethod("add", new Class[] {java.awt.Component.class, java.lang.Object.class, int.class})); // NOI18N
            methods[METHOD_add6].setDisplayName ( "" );
            methods[METHOD_addControlListener7] = new MethodDescriptor(prefuse.Display.class.getMethod("addControlListener", new Class[] {prefuse.controls.Control.class})); // NOI18N
            methods[METHOD_addControlListener7].setDisplayName ( "" );
            methods[METHOD_addItemBoundsListener8] = new MethodDescriptor(prefuse.Display.class.getMethod("addItemBoundsListener", new Class[] {prefuse.util.display.ItemBoundsListener.class})); // NOI18N
            methods[METHOD_addItemBoundsListener8].setDisplayName ( "" );
            methods[METHOD_addNotify9] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("addNotify", new Class[] {})); // NOI18N
            methods[METHOD_addNotify9].setDisplayName ( "" );
            methods[METHOD_addPropertyChangeListener10] = new MethodDescriptor(java.awt.Container.class.getMethod("addPropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_addPropertyChangeListener10].setDisplayName ( "" );
            methods[METHOD_animatePan11] = new MethodDescriptor(prefuse.Display.class.getMethod("animatePan", new Class[] {double.class, double.class, long.class})); // NOI18N
            methods[METHOD_animatePan11].setDisplayName ( "" );
            methods[METHOD_animatePanAbs12] = new MethodDescriptor(prefuse.Display.class.getMethod("animatePanAbs", new Class[] {double.class, double.class, long.class})); // NOI18N
            methods[METHOD_animatePanAbs12].setDisplayName ( "" );
            methods[METHOD_animatePanAndZoomTo13] = new MethodDescriptor(prefuse.Display.class.getMethod("animatePanAndZoomTo", new Class[] {java.awt.geom.Point2D.class, double.class, long.class})); // NOI18N
            methods[METHOD_animatePanAndZoomTo13].setDisplayName ( "" );
            methods[METHOD_animatePanAndZoomToAbs14] = new MethodDescriptor(prefuse.Display.class.getMethod("animatePanAndZoomToAbs", new Class[] {java.awt.geom.Point2D.class, double.class, long.class})); // NOI18N
            methods[METHOD_animatePanAndZoomToAbs14].setDisplayName ( "" );
            methods[METHOD_animatePanTo15] = new MethodDescriptor(prefuse.Display.class.getMethod("animatePanTo", new Class[] {java.awt.geom.Point2D.class, long.class})); // NOI18N
            methods[METHOD_animatePanTo15].setDisplayName ( "" );
            methods[METHOD_animatePanToAbs16] = new MethodDescriptor(prefuse.Display.class.getMethod("animatePanToAbs", new Class[] {java.awt.geom.Point2D.class, long.class})); // NOI18N
            methods[METHOD_animatePanToAbs16].setDisplayName ( "" );
            methods[METHOD_animateZoom17] = new MethodDescriptor(prefuse.Display.class.getMethod("animateZoom", new Class[] {java.awt.geom.Point2D.class, double.class, long.class})); // NOI18N
            methods[METHOD_animateZoom17].setDisplayName ( "" );
            methods[METHOD_animateZoomAbs18] = new MethodDescriptor(prefuse.Display.class.getMethod("animateZoomAbs", new Class[] {java.awt.geom.Point2D.class, double.class, long.class})); // NOI18N
            methods[METHOD_animateZoomAbs18].setDisplayName ( "" );
            methods[METHOD_applyComponentOrientation19] = new MethodDescriptor(java.awt.Container.class.getMethod("applyComponentOrientation", new Class[] {java.awt.ComponentOrientation.class})); // NOI18N
            methods[METHOD_applyComponentOrientation19].setDisplayName ( "" );
            methods[METHOD_areFocusTraversalKeysSet20] = new MethodDescriptor(java.awt.Container.class.getMethod("areFocusTraversalKeysSet", new Class[] {int.class})); // NOI18N
            methods[METHOD_areFocusTraversalKeysSet20].setDisplayName ( "" );
            methods[METHOD_bounds21] = new MethodDescriptor(java.awt.Component.class.getMethod("bounds", new Class[] {})); // NOI18N
            methods[METHOD_bounds21].setDisplayName ( "" );
            methods[METHOD_checkImage22] = new MethodDescriptor(java.awt.Component.class.getMethod("checkImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_checkImage22].setDisplayName ( "" );
            methods[METHOD_checkImage23] = new MethodDescriptor(java.awt.Component.class.getMethod("checkImage", new Class[] {java.awt.Image.class, int.class, int.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_checkImage23].setDisplayName ( "" );
            methods[METHOD_clearDamage24] = new MethodDescriptor(prefuse.Display.class.getMethod("clearDamage", new Class[] {})); // NOI18N
            methods[METHOD_clearDamage24].setDisplayName ( "" );
            methods[METHOD_computeVisibleRect25] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("computeVisibleRect", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_computeVisibleRect25].setDisplayName ( "" );
            methods[METHOD_contains26] = new MethodDescriptor(java.awt.Component.class.getMethod("contains", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_contains26].setDisplayName ( "" );
            methods[METHOD_contains27] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("contains", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_contains27].setDisplayName ( "" );
            methods[METHOD_countComponents28] = new MethodDescriptor(java.awt.Container.class.getMethod("countComponents", new Class[] {})); // NOI18N
            methods[METHOD_countComponents28].setDisplayName ( "" );
            methods[METHOD_createImage29] = new MethodDescriptor(java.awt.Component.class.getMethod("createImage", new Class[] {java.awt.image.ImageProducer.class})); // NOI18N
            methods[METHOD_createImage29].setDisplayName ( "" );
            methods[METHOD_createImage30] = new MethodDescriptor(java.awt.Component.class.getMethod("createImage", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_createImage30].setDisplayName ( "" );
            methods[METHOD_createToolTip31] = new MethodDescriptor(prefuse.Display.class.getMethod("createToolTip", new Class[] {})); // NOI18N
            methods[METHOD_createToolTip31].setDisplayName ( "" );
            methods[METHOD_createVolatileImage32] = new MethodDescriptor(java.awt.Component.class.getMethod("createVolatileImage", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_createVolatileImage32].setDisplayName ( "" );
            methods[METHOD_createVolatileImage33] = new MethodDescriptor(java.awt.Component.class.getMethod("createVolatileImage", new Class[] {int.class, int.class, java.awt.ImageCapabilities.class})); // NOI18N
            methods[METHOD_createVolatileImage33].setDisplayName ( "" );
            methods[METHOD_damageReport34] = new MethodDescriptor(prefuse.Display.class.getMethod("damageReport", new Class[] {java.awt.geom.Rectangle2D.class})); // NOI18N
            methods[METHOD_damageReport34].setDisplayName ( "" );
            methods[METHOD_damageReport35] = new MethodDescriptor(prefuse.Display.class.getMethod("damageReport", new Class[] {})); // NOI18N
            methods[METHOD_damageReport35].setDisplayName ( "" );
            methods[METHOD_deliverEvent36] = new MethodDescriptor(java.awt.Container.class.getMethod("deliverEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_deliverEvent36].setDisplayName ( "" );
            methods[METHOD_demo37] = new MethodDescriptor(prefuse.demos.RadialGraphView_First_Task.class.getMethod("demo", new Class[] {})); // NOI18N
            methods[METHOD_demo37].setDisplayName ( "" );
            methods[METHOD_demo38] = new MethodDescriptor(prefuse.demos.RadialGraphView_First_Task.class.getMethod("demo", new Class[] {java.lang.String.class, java.lang.String.class})); // NOI18N
            methods[METHOD_demo38].setDisplayName ( "" );
            methods[METHOD_demo39] = new MethodDescriptor(prefuse.demos.RadialGraphView_First_Task.class.getMethod("demo", new Class[] {prefuse.data.Graph.class, java.lang.String.class})); // NOI18N
            methods[METHOD_demo39].setDisplayName ( "" );
            methods[METHOD_disable40] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("disable", new Class[] {})); // NOI18N
            methods[METHOD_disable40].setDisplayName ( "" );
            methods[METHOD_dispatchEvent41] = new MethodDescriptor(java.awt.Component.class.getMethod("dispatchEvent", new Class[] {java.awt.AWTEvent.class})); // NOI18N
            methods[METHOD_dispatchEvent41].setDisplayName ( "" );
            methods[METHOD_doLayout42] = new MethodDescriptor(java.awt.Container.class.getMethod("doLayout", new Class[] {})); // NOI18N
            methods[METHOD_doLayout42].setDisplayName ( "" );
            methods[METHOD_editText43] = new MethodDescriptor(prefuse.Display.class.getMethod("editText", new Class[] {prefuse.visual.VisualItem.class, java.lang.String.class})); // NOI18N
            methods[METHOD_editText43].setDisplayName ( "" );
            methods[METHOD_editText44] = new MethodDescriptor(prefuse.Display.class.getMethod("editText", new Class[] {prefuse.visual.VisualItem.class, java.lang.String.class, java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_editText44].setDisplayName ( "" );
            methods[METHOD_editText45] = new MethodDescriptor(prefuse.Display.class.getMethod("editText", new Class[] {java.lang.String.class, java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_editText45].setDisplayName ( "" );
            methods[METHOD_enable46] = new MethodDescriptor(java.awt.Component.class.getMethod("enable", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_enable46].setDisplayName ( "" );
            methods[METHOD_enable47] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("enable", new Class[] {})); // NOI18N
            methods[METHOD_enable47].setDisplayName ( "" );
            methods[METHOD_enableInputMethods48] = new MethodDescriptor(java.awt.Component.class.getMethod("enableInputMethods", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_enableInputMethods48].setDisplayName ( "" );
            methods[METHOD_findComponentAt49] = new MethodDescriptor(java.awt.Container.class.getMethod("findComponentAt", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_findComponentAt49].setDisplayName ( "" );
            methods[METHOD_findComponentAt50] = new MethodDescriptor(java.awt.Container.class.getMethod("findComponentAt", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_findComponentAt50].setDisplayName ( "" );
            methods[METHOD_findItem51] = new MethodDescriptor(prefuse.Display.class.getMethod("findItem", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_findItem51].setDisplayName ( "" );
            methods[METHOD_firePropertyChange52] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, byte.class, byte.class})); // NOI18N
            methods[METHOD_firePropertyChange52].setDisplayName ( "" );
            methods[METHOD_firePropertyChange53] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, short.class, short.class})); // NOI18N
            methods[METHOD_firePropertyChange53].setDisplayName ( "" );
            methods[METHOD_firePropertyChange54] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, long.class, long.class})); // NOI18N
            methods[METHOD_firePropertyChange54].setDisplayName ( "" );
            methods[METHOD_firePropertyChange55] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, float.class, float.class})); // NOI18N
            methods[METHOD_firePropertyChange55].setDisplayName ( "" );
            methods[METHOD_firePropertyChange56] = new MethodDescriptor(java.awt.Component.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, double.class, double.class})); // NOI18N
            methods[METHOD_firePropertyChange56].setDisplayName ( "" );
            methods[METHOD_firePropertyChange57] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, boolean.class, boolean.class})); // NOI18N
            methods[METHOD_firePropertyChange57].setDisplayName ( "" );
            methods[METHOD_firePropertyChange58] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, int.class, int.class})); // NOI18N
            methods[METHOD_firePropertyChange58].setDisplayName ( "" );
            methods[METHOD_firePropertyChange59] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("firePropertyChange", new Class[] {java.lang.String.class, char.class, char.class})); // NOI18N
            methods[METHOD_firePropertyChange59].setDisplayName ( "" );
            methods[METHOD_getAbsoluteCoordinate60] = new MethodDescriptor(prefuse.Display.class.getMethod("getAbsoluteCoordinate", new Class[] {java.awt.geom.Point2D.class, java.awt.geom.Point2D.class})); // NOI18N
            methods[METHOD_getAbsoluteCoordinate60].setDisplayName ( "" );
            methods[METHOD_getActionForKeyStroke61] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getActionForKeyStroke", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_getActionForKeyStroke61].setDisplayName ( "" );
            methods[METHOD_getBaseline62] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getBaseline", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getBaseline62].setDisplayName ( "" );
            methods[METHOD_getBounds63] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getBounds", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_getBounds63].setDisplayName ( "" );
            methods[METHOD_getClientProperty64] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getClientProperty", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_getClientProperty64].setDisplayName ( "" );
            methods[METHOD_getComponentAt65] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentAt", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_getComponentAt65].setDisplayName ( "" );
            methods[METHOD_getComponentAt66] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentAt", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_getComponentAt66].setDisplayName ( "" );
            methods[METHOD_getComponentZOrder67] = new MethodDescriptor(java.awt.Container.class.getMethod("getComponentZOrder", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_getComponentZOrder67].setDisplayName ( "" );
            methods[METHOD_getConditionForKeyStroke68] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getConditionForKeyStroke", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_getConditionForKeyStroke68].setDisplayName ( "" );
            methods[METHOD_getDefaultLocale69] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getDefaultLocale", new Class[] {})); // NOI18N
            methods[METHOD_getDefaultLocale69].setDisplayName ( "" );
            methods[METHOD_getFocusTraversalKeys70] = new MethodDescriptor(java.awt.Container.class.getMethod("getFocusTraversalKeys", new Class[] {int.class})); // NOI18N
            methods[METHOD_getFocusTraversalKeys70].setDisplayName ( "" );
            methods[METHOD_getFontMetrics71] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getFontMetrics", new Class[] {java.awt.Font.class})); // NOI18N
            methods[METHOD_getFontMetrics71].setDisplayName ( "" );
            methods[METHOD_getInsets72] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getInsets", new Class[] {java.awt.Insets.class})); // NOI18N
            methods[METHOD_getInsets72].setDisplayName ( "" );
            methods[METHOD_getItemBounds73] = new MethodDescriptor(prefuse.Display.class.getMethod("getItemBounds", new Class[] {java.awt.geom.Rectangle2D.class})); // NOI18N
            methods[METHOD_getItemBounds73].setDisplayName ( "" );
            methods[METHOD_getListeners74] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getListeners", new Class[] {java.lang.Class.class})); // NOI18N
            methods[METHOD_getListeners74].setDisplayName ( "" );
            methods[METHOD_getLocation75] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getLocation", new Class[] {java.awt.Point.class})); // NOI18N
            methods[METHOD_getLocation75].setDisplayName ( "" );
            methods[METHOD_getMousePosition76] = new MethodDescriptor(java.awt.Container.class.getMethod("getMousePosition", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_getMousePosition76].setDisplayName ( "" );
            methods[METHOD_getPopupLocation77] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getPopupLocation", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getPopupLocation77].setDisplayName ( "" );
            methods[METHOD_getPredicate78] = new MethodDescriptor(prefuse.Display.class.getMethod("getPredicate", new Class[] {})); // NOI18N
            methods[METHOD_getPredicate78].setDisplayName ( "" );
            methods[METHOD_getPropertyChangeListeners79] = new MethodDescriptor(java.awt.Component.class.getMethod("getPropertyChangeListeners", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_getPropertyChangeListeners79].setDisplayName ( "" );
            methods[METHOD_getSize80] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getSize", new Class[] {java.awt.Dimension.class})); // NOI18N
            methods[METHOD_getSize80].setDisplayName ( "" );
            methods[METHOD_getToolTipLocation81] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getToolTipLocation", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getToolTipLocation81].setDisplayName ( "" );
            methods[METHOD_getToolTipText82] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("getToolTipText", new Class[] {java.awt.event.MouseEvent.class})); // NOI18N
            methods[METHOD_getToolTipText82].setDisplayName ( "" );
            methods[METHOD_gotFocus83] = new MethodDescriptor(java.awt.Component.class.getMethod("gotFocus", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_gotFocus83].setDisplayName ( "" );
            methods[METHOD_grabFocus84] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("grabFocus", new Class[] {})); // NOI18N
            methods[METHOD_grabFocus84].setDisplayName ( "" );
            methods[METHOD_handleEvent85] = new MethodDescriptor(java.awt.Component.class.getMethod("handleEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_handleEvent85].setDisplayName ( "" );
            methods[METHOD_hasFocus86] = new MethodDescriptor(java.awt.Component.class.getMethod("hasFocus", new Class[] {})); // NOI18N
            methods[METHOD_hasFocus86].setDisplayName ( "" );
            methods[METHOD_hide87] = new MethodDescriptor(java.awt.Component.class.getMethod("hide", new Class[] {})); // NOI18N
            methods[METHOD_hide87].setDisplayName ( "" );
            methods[METHOD_imageUpdate88] = new MethodDescriptor(java.awt.Component.class.getMethod("imageUpdate", new Class[] {java.awt.Image.class, int.class, int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_imageUpdate88].setDisplayName ( "" );
            methods[METHOD_insets89] = new MethodDescriptor(java.awt.Container.class.getMethod("insets", new Class[] {})); // NOI18N
            methods[METHOD_insets89].setDisplayName ( "" );
            methods[METHOD_inside90] = new MethodDescriptor(java.awt.Component.class.getMethod("inside", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_inside90].setDisplayName ( "" );
            methods[METHOD_invalidate91] = new MethodDescriptor(prefuse.Display.class.getMethod("invalidate", new Class[] {})); // NOI18N
            methods[METHOD_invalidate91].setDisplayName ( "" );
            methods[METHOD_isAncestorOf92] = new MethodDescriptor(java.awt.Container.class.getMethod("isAncestorOf", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_isAncestorOf92].setDisplayName ( "" );
            methods[METHOD_isFocusCycleRoot93] = new MethodDescriptor(java.awt.Container.class.getMethod("isFocusCycleRoot", new Class[] {java.awt.Container.class})); // NOI18N
            methods[METHOD_isFocusCycleRoot93].setDisplayName ( "" );
            methods[METHOD_isLightweightComponent94] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("isLightweightComponent", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_isLightweightComponent94].setDisplayName ( "" );
            methods[METHOD_keyDown95] = new MethodDescriptor(java.awt.Component.class.getMethod("keyDown", new Class[] {java.awt.Event.class, int.class})); // NOI18N
            methods[METHOD_keyDown95].setDisplayName ( "" );
            methods[METHOD_keyUp96] = new MethodDescriptor(java.awt.Component.class.getMethod("keyUp", new Class[] {java.awt.Event.class, int.class})); // NOI18N
            methods[METHOD_keyUp96].setDisplayName ( "" );
            methods[METHOD_layout97] = new MethodDescriptor(java.awt.Container.class.getMethod("layout", new Class[] {})); // NOI18N
            methods[METHOD_layout97].setDisplayName ( "" );
            methods[METHOD_list98] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {})); // NOI18N
            methods[METHOD_list98].setDisplayName ( "" );
            methods[METHOD_list99] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {java.io.PrintStream.class})); // NOI18N
            methods[METHOD_list99].setDisplayName ( "" );
            methods[METHOD_list100] = new MethodDescriptor(java.awt.Component.class.getMethod("list", new Class[] {java.io.PrintWriter.class})); // NOI18N
            methods[METHOD_list100].setDisplayName ( "" );
            methods[METHOD_list101] = new MethodDescriptor(java.awt.Container.class.getMethod("list", new Class[] {java.io.PrintStream.class, int.class})); // NOI18N
            methods[METHOD_list101].setDisplayName ( "" );
            methods[METHOD_list102] = new MethodDescriptor(java.awt.Container.class.getMethod("list", new Class[] {java.io.PrintWriter.class, int.class})); // NOI18N
            methods[METHOD_list102].setDisplayName ( "" );
            methods[METHOD_locate103] = new MethodDescriptor(java.awt.Container.class.getMethod("locate", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_locate103].setDisplayName ( "" );
            methods[METHOD_location104] = new MethodDescriptor(java.awt.Component.class.getMethod("location", new Class[] {})); // NOI18N
            methods[METHOD_location104].setDisplayName ( "" );
            methods[METHOD_lostFocus105] = new MethodDescriptor(java.awt.Component.class.getMethod("lostFocus", new Class[] {java.awt.Event.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_lostFocus105].setDisplayName ( "" );
            methods[METHOD_main106] = new MethodDescriptor(prefuse.demos.RadialGraphView_First_Task.class.getMethod("main", new Class[] {java.lang.String[].class})); // NOI18N
            methods[METHOD_main106].setDisplayName ( "" );
            methods[METHOD_minimumSize107] = new MethodDescriptor(java.awt.Container.class.getMethod("minimumSize", new Class[] {})); // NOI18N
            methods[METHOD_minimumSize107].setDisplayName ( "" );
            methods[METHOD_mouseDown108] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseDown", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseDown108].setDisplayName ( "" );
            methods[METHOD_mouseDrag109] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseDrag", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseDrag109].setDisplayName ( "" );
            methods[METHOD_mouseEnter110] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseEnter", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseEnter110].setDisplayName ( "" );
            methods[METHOD_mouseExit111] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseExit", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseExit111].setDisplayName ( "" );
            methods[METHOD_mouseMove112] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseMove", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseMove112].setDisplayName ( "" );
            methods[METHOD_mouseUp113] = new MethodDescriptor(java.awt.Component.class.getMethod("mouseUp", new Class[] {java.awt.Event.class, int.class, int.class})); // NOI18N
            methods[METHOD_mouseUp113].setDisplayName ( "" );
            methods[METHOD_move114] = new MethodDescriptor(java.awt.Component.class.getMethod("move", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_move114].setDisplayName ( "" );
            methods[METHOD_nextFocus115] = new MethodDescriptor(java.awt.Component.class.getMethod("nextFocus", new Class[] {})); // NOI18N
            methods[METHOD_nextFocus115].setDisplayName ( "" );
            methods[METHOD_paint116] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paint", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paint116].setDisplayName ( "" );
            methods[METHOD_paintAll117] = new MethodDescriptor(java.awt.Component.class.getMethod("paintAll", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paintAll117].setDisplayName ( "" );
            methods[METHOD_paintComponent118] = new MethodDescriptor(prefuse.Display.class.getMethod("paintComponent", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paintComponent118].setDisplayName ( "" );
            methods[METHOD_paintComponents119] = new MethodDescriptor(java.awt.Container.class.getMethod("paintComponents", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_paintComponents119].setDisplayName ( "" );
            methods[METHOD_paintDisplay120] = new MethodDescriptor(prefuse.Display.class.getMethod("paintDisplay", new Class[] {java.awt.Graphics2D.class, java.awt.Dimension.class})); // NOI18N
            methods[METHOD_paintDisplay120].setDisplayName ( "" );
            methods[METHOD_paintImmediately121] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paintImmediately", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_paintImmediately121].setDisplayName ( "" );
            methods[METHOD_paintImmediately122] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("paintImmediately", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_paintImmediately122].setDisplayName ( "" );
            methods[METHOD_pan123] = new MethodDescriptor(prefuse.Display.class.getMethod("pan", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_pan123].setDisplayName ( "" );
            methods[METHOD_panAbs124] = new MethodDescriptor(prefuse.Display.class.getMethod("panAbs", new Class[] {double.class, double.class})); // NOI18N
            methods[METHOD_panAbs124].setDisplayName ( "" );
            methods[METHOD_panTo125] = new MethodDescriptor(prefuse.Display.class.getMethod("panTo", new Class[] {java.awt.geom.Point2D.class})); // NOI18N
            methods[METHOD_panTo125].setDisplayName ( "" );
            methods[METHOD_panToAbs126] = new MethodDescriptor(prefuse.Display.class.getMethod("panToAbs", new Class[] {java.awt.geom.Point2D.class})); // NOI18N
            methods[METHOD_panToAbs126].setDisplayName ( "" );
            methods[METHOD_postEvent127] = new MethodDescriptor(java.awt.Component.class.getMethod("postEvent", new Class[] {java.awt.Event.class})); // NOI18N
            methods[METHOD_postEvent127].setDisplayName ( "" );
            methods[METHOD_preferredSize128] = new MethodDescriptor(java.awt.Container.class.getMethod("preferredSize", new Class[] {})); // NOI18N
            methods[METHOD_preferredSize128].setDisplayName ( "" );
            methods[METHOD_prepareImage129] = new MethodDescriptor(java.awt.Component.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_prepareImage129].setDisplayName ( "" );
            methods[METHOD_prepareImage130] = new MethodDescriptor(java.awt.Component.class.getMethod("prepareImage", new Class[] {java.awt.Image.class, int.class, int.class, java.awt.image.ImageObserver.class})); // NOI18N
            methods[METHOD_prepareImage130].setDisplayName ( "" );
            methods[METHOD_print131] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("print", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_print131].setDisplayName ( "" );
            methods[METHOD_printAll132] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("printAll", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_printAll132].setDisplayName ( "" );
            methods[METHOD_printComponents133] = new MethodDescriptor(java.awt.Container.class.getMethod("printComponents", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_printComponents133].setDisplayName ( "" );
            methods[METHOD_putClientProperty134] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("putClientProperty", new Class[] {java.lang.Object.class, java.lang.Object.class})); // NOI18N
            methods[METHOD_putClientProperty134].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction135] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, java.lang.String.class, javax.swing.KeyStroke.class, int.class})); // NOI18N
            methods[METHOD_registerKeyboardAction135].setDisplayName ( "" );
            methods[METHOD_registerKeyboardAction136] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("registerKeyboardAction", new Class[] {java.awt.event.ActionListener.class, javax.swing.KeyStroke.class, int.class})); // NOI18N
            methods[METHOD_registerKeyboardAction136].setDisplayName ( "" );
            methods[METHOD_remove137] = new MethodDescriptor(java.awt.Component.class.getMethod("remove", new Class[] {java.awt.MenuComponent.class})); // NOI18N
            methods[METHOD_remove137].setDisplayName ( "" );
            methods[METHOD_remove138] = new MethodDescriptor(java.awt.Container.class.getMethod("remove", new Class[] {int.class})); // NOI18N
            methods[METHOD_remove138].setDisplayName ( "" );
            methods[METHOD_remove139] = new MethodDescriptor(java.awt.Container.class.getMethod("remove", new Class[] {java.awt.Component.class})); // NOI18N
            methods[METHOD_remove139].setDisplayName ( "" );
            methods[METHOD_removeAll140] = new MethodDescriptor(java.awt.Container.class.getMethod("removeAll", new Class[] {})); // NOI18N
            methods[METHOD_removeAll140].setDisplayName ( "" );
            methods[METHOD_removeControlListener141] = new MethodDescriptor(prefuse.Display.class.getMethod("removeControlListener", new Class[] {prefuse.controls.Control.class})); // NOI18N
            methods[METHOD_removeControlListener141].setDisplayName ( "" );
            methods[METHOD_removeItemBoundsListener142] = new MethodDescriptor(prefuse.Display.class.getMethod("removeItemBoundsListener", new Class[] {prefuse.util.display.ItemBoundsListener.class})); // NOI18N
            methods[METHOD_removeItemBoundsListener142].setDisplayName ( "" );
            methods[METHOD_removeNotify143] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("removeNotify", new Class[] {})); // NOI18N
            methods[METHOD_removeNotify143].setDisplayName ( "" );
            methods[METHOD_removePropertyChangeListener144] = new MethodDescriptor(java.awt.Component.class.getMethod("removePropertyChangeListener", new Class[] {java.lang.String.class, java.beans.PropertyChangeListener.class})); // NOI18N
            methods[METHOD_removePropertyChangeListener144].setDisplayName ( "" );
            methods[METHOD_renderImmediate145] = new MethodDescriptor(prefuse.Display.class.getMethod("renderImmediate", new Class[] {prefuse.visual.VisualItem.class})); // NOI18N
            methods[METHOD_renderImmediate145].setDisplayName ( "" );
            methods[METHOD_repaint146] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {})); // NOI18N
            methods[METHOD_repaint146].setDisplayName ( "" );
            methods[METHOD_repaint147] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {long.class})); // NOI18N
            methods[METHOD_repaint147].setDisplayName ( "" );
            methods[METHOD_repaint148] = new MethodDescriptor(java.awt.Component.class.getMethod("repaint", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_repaint148].setDisplayName ( "" );
            methods[METHOD_repaint149] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("repaint", new Class[] {long.class, int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_repaint149].setDisplayName ( "" );
            methods[METHOD_repaint150] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("repaint", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_repaint150].setDisplayName ( "" );
            methods[METHOD_repaintImmediate151] = new MethodDescriptor(prefuse.Display.class.getMethod("repaintImmediate", new Class[] {})); // NOI18N
            methods[METHOD_repaintImmediate151].setDisplayName ( "" );
            methods[METHOD_requestDefaultFocus152] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestDefaultFocus", new Class[] {})); // NOI18N
            methods[METHOD_requestDefaultFocus152].setDisplayName ( "" );
            methods[METHOD_requestFocus153] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocus", new Class[] {})); // NOI18N
            methods[METHOD_requestFocus153].setDisplayName ( "" );
            methods[METHOD_requestFocus154] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocus", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_requestFocus154].setDisplayName ( "" );
            methods[METHOD_requestFocusInWindow155] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("requestFocusInWindow", new Class[] {})); // NOI18N
            methods[METHOD_requestFocusInWindow155].setDisplayName ( "" );
            methods[METHOD_reset156] = new MethodDescriptor(prefuse.Display.class.getMethod("reset", new Class[] {})); // NOI18N
            methods[METHOD_reset156].setDisplayName ( "" );
            methods[METHOD_resetKeyboardActions157] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("resetKeyboardActions", new Class[] {})); // NOI18N
            methods[METHOD_resetKeyboardActions157].setDisplayName ( "" );
            methods[METHOD_reshape158] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("reshape", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_reshape158].setDisplayName ( "" );
            methods[METHOD_resize159] = new MethodDescriptor(java.awt.Component.class.getMethod("resize", new Class[] {int.class, int.class})); // NOI18N
            methods[METHOD_resize159].setDisplayName ( "" );
            methods[METHOD_resize160] = new MethodDescriptor(java.awt.Component.class.getMethod("resize", new Class[] {java.awt.Dimension.class})); // NOI18N
            methods[METHOD_resize160].setDisplayName ( "" );
            methods[METHOD_revalidate161] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("revalidate", new Class[] {})); // NOI18N
            methods[METHOD_revalidate161].setDisplayName ( "" );
            methods[METHOD_rotate162] = new MethodDescriptor(prefuse.Display.class.getMethod("rotate", new Class[] {java.awt.geom.Point2D.class, double.class})); // NOI18N
            methods[METHOD_rotate162].setDisplayName ( "" );
            methods[METHOD_rotateAbs163] = new MethodDescriptor(prefuse.Display.class.getMethod("rotateAbs", new Class[] {java.awt.geom.Point2D.class, double.class})); // NOI18N
            methods[METHOD_rotateAbs163].setDisplayName ( "" );
            methods[METHOD_saveImage164] = new MethodDescriptor(prefuse.Display.class.getMethod("saveImage", new Class[] {java.io.OutputStream.class, java.lang.String.class, double.class})); // NOI18N
            methods[METHOD_saveImage164].setDisplayName ( "" );
            methods[METHOD_scrollRectToVisible165] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("scrollRectToVisible", new Class[] {java.awt.Rectangle.class})); // NOI18N
            methods[METHOD_scrollRectToVisible165].setDisplayName ( "" );
            methods[METHOD_setBackgroundImage166] = new MethodDescriptor(prefuse.Display.class.getMethod("setBackgroundImage", new Class[] {java.awt.Image.class, boolean.class, boolean.class})); // NOI18N
            methods[METHOD_setBackgroundImage166].setDisplayName ( "" );
            methods[METHOD_setBackgroundImage167] = new MethodDescriptor(prefuse.Display.class.getMethod("setBackgroundImage", new Class[] {java.lang.String.class, boolean.class, boolean.class})); // NOI18N
            methods[METHOD_setBackgroundImage167].setDisplayName ( "" );
            methods[METHOD_setBounds168] = new MethodDescriptor(prefuse.Display.class.getMethod("setBounds", new Class[] {int.class, int.class, int.class, int.class})); // NOI18N
            methods[METHOD_setBounds168].setDisplayName ( "" );
            methods[METHOD_setComponentZOrder169] = new MethodDescriptor(java.awt.Container.class.getMethod("setComponentZOrder", new Class[] {java.awt.Component.class, int.class})); // NOI18N
            methods[METHOD_setComponentZOrder169].setDisplayName ( "" );
            methods[METHOD_setDefaultLocale170] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("setDefaultLocale", new Class[] {java.util.Locale.class})); // NOI18N
            methods[METHOD_setDefaultLocale170].setDisplayName ( "" );
            methods[METHOD_setPredicate171] = new MethodDescriptor(prefuse.Display.class.getMethod("setPredicate", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_setPredicate171].setDisplayName ( "" );
            methods[METHOD_show172] = new MethodDescriptor(java.awt.Component.class.getMethod("show", new Class[] {})); // NOI18N
            methods[METHOD_show172].setDisplayName ( "" );
            methods[METHOD_show173] = new MethodDescriptor(java.awt.Component.class.getMethod("show", new Class[] {boolean.class})); // NOI18N
            methods[METHOD_show173].setDisplayName ( "" );
            methods[METHOD_size174] = new MethodDescriptor(java.awt.Component.class.getMethod("size", new Class[] {})); // NOI18N
            methods[METHOD_size174].setDisplayName ( "" );
            methods[METHOD_stopEditing175] = new MethodDescriptor(prefuse.Display.class.getMethod("stopEditing", new Class[] {})); // NOI18N
            methods[METHOD_stopEditing175].setDisplayName ( "" );
            methods[METHOD_toString176] = new MethodDescriptor(java.awt.Component.class.getMethod("toString", new Class[] {})); // NOI18N
            methods[METHOD_toString176].setDisplayName ( "" );
            methods[METHOD_transferFocus177] = new MethodDescriptor(java.awt.Component.class.getMethod("transferFocus", new Class[] {})); // NOI18N
            methods[METHOD_transferFocus177].setDisplayName ( "" );
            methods[METHOD_transferFocusBackward178] = new MethodDescriptor(java.awt.Component.class.getMethod("transferFocusBackward", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusBackward178].setDisplayName ( "" );
            methods[METHOD_transferFocusDownCycle179] = new MethodDescriptor(java.awt.Container.class.getMethod("transferFocusDownCycle", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusDownCycle179].setDisplayName ( "" );
            methods[METHOD_transferFocusUpCycle180] = new MethodDescriptor(java.awt.Component.class.getMethod("transferFocusUpCycle", new Class[] {})); // NOI18N
            methods[METHOD_transferFocusUpCycle180].setDisplayName ( "" );
            methods[METHOD_unregisterKeyboardAction181] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("unregisterKeyboardAction", new Class[] {javax.swing.KeyStroke.class})); // NOI18N
            methods[METHOD_unregisterKeyboardAction181].setDisplayName ( "" );
            methods[METHOD_update182] = new MethodDescriptor(prefuse.Display.class.getMethod("update", new Class[] {java.awt.Graphics.class})); // NOI18N
            methods[METHOD_update182].setDisplayName ( "" );
            methods[METHOD_updateUI183] = new MethodDescriptor(javax.swing.JComponent.class.getMethod("updateUI", new Class[] {})); // NOI18N
            methods[METHOD_updateUI183].setDisplayName ( "" );
            methods[METHOD_validate184] = new MethodDescriptor(java.awt.Container.class.getMethod("validate", new Class[] {})); // NOI18N
            methods[METHOD_validate184].setDisplayName ( "" );
            methods[METHOD_zoom185] = new MethodDescriptor(prefuse.Display.class.getMethod("zoom", new Class[] {java.awt.geom.Point2D.class, double.class})); // NOI18N
            methods[METHOD_zoom185].setDisplayName ( "" );
            methods[METHOD_zoomAbs186] = new MethodDescriptor(prefuse.Display.class.getMethod("zoomAbs", new Class[] {java.awt.geom.Point2D.class, double.class})); // NOI18N
            methods[METHOD_zoomAbs186].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods

        // Here you can add code for customizing the methods array.
        
        return methods;     }//GEN-LAST:Methods
    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons
    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx

//GEN-FIRST:Superclass
    // Here you can add code for customizing the Superclass BeanInfo.
//GEN-LAST:Superclass
    /**
     * Gets the bean's
     * <code>BeanDescriptor</code>s.
     *
     * @return BeanDescriptor describing the editable properties of this bean.
     * May return null if the information should be obtained by automatic
     * analysis.
     */
    public BeanDescriptor getBeanDescriptor() {
        return getBdescriptor();
    }

    /**
     * Gets the bean's
     * <code>PropertyDescriptor</code>s.
     *
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean. May return null if the information
     * should be obtained by automatic analysis. <p> If a property is indexed,
     * then its entry in the result array will belong to the
     * IndexedPropertyDescriptor subclass of PropertyDescriptor. A client of
     * getPropertyDescriptors can use "instanceof" to check if a given
     * PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getPdescriptor();
    }

    /**
     * Gets the bean's
     * <code>EventSetDescriptor</code>s.
     *
     * @return An array of EventSetDescriptors describing the kinds of events
     * fired by this bean. May return null if the information should be obtained
     * by automatic analysis.
     */
    public EventSetDescriptor[] getEventSetDescriptors() {
        return getEdescriptor();
    }

    /**
     * Gets the bean's
     * <code>MethodDescriptor</code>s.
     *
     * @return An array of MethodDescriptors describing the methods implemented
     * by this bean. May return null if the information should be obtained by
     * automatic analysis.
     */
    public MethodDescriptor[] getMethodDescriptors() {
        return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are
     * customizing the bean.
     *
     * @return Index of default property in the PropertyDescriptor array
     * returned by getPropertyDescriptors. <P>	Returns -1 if there is no default
     * property.
     */
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will mostly
     * commonly be used by human's when using the bean.
     *
     * @return Index of default event in the EventSetDescriptor array returned
     * by getEventSetDescriptors. <P>	Returns -1 if there is no default event.
     */
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to represent the
     * bean in toolboxes, toolbars, etc. Icon images will typically be GIFs, but
     * may in future include other formats. <p> Beans aren't required to provide
     * icons and may return null from this method. <p> There are four possible
     * flavors of icons (16x16 color, 32x32 color, 16x16 mono, 32x32 mono). If a
     * bean choses to only support a single icon we recommend supporting 16x16
     * color. <p> We recommend that icons have a "transparent" background so
     * they can be rendered onto an existing background.
     *
     * @param iconKind The kind of icon requested. This should be one of the
     * constant values ICON_COLOR_16x16, ICON_COLOR_32x32, ICON_MONO_16x16, or
     * ICON_MONO_32x32.
     * @return An image object representing the requested icon. May return null
     * if no suitable icon is available.
     */
    public java.awt.Image getIcon(int iconKind) {
        switch (iconKind) {
            case ICON_COLOR_16x16:
                if (iconNameC16 == null) {
                    return null;
                } else {
                    if (iconColor16 == null) {
                        iconColor16 = loadImage(iconNameC16);
                    }
                    return iconColor16;
                }
            case ICON_COLOR_32x32:
                if (iconNameC32 == null) {
                    return null;
                } else {
                    if (iconColor32 == null) {
                        iconColor32 = loadImage(iconNameC32);
                    }
                    return iconColor32;
                }
            case ICON_MONO_16x16:
                if (iconNameM16 == null) {
                    return null;
                } else {
                    if (iconMono16 == null) {
                        iconMono16 = loadImage(iconNameM16);
                    }
                    return iconMono16;
                }
            case ICON_MONO_32x32:
                if (iconNameM32 == null) {
                    return null;
                } else {
                    if (iconMono32 == null) {
                        iconMono32 = loadImage(iconNameM32);
                    }
                    return iconMono32;
                }
            default:
                return null;
        }
    }
}
