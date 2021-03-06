<?xml version="1.0" encoding="UTF-8"?>
<!-- ============================================================= -->
<!--                    HEADER                                     -->
<!-- ============================================================= -->
<!--  MODULE:    DITA Utilities Domain                             -->
<!--  VERSION:   1.2                                               -->
<!--  DATE:      November 2009                                     -->
<!--                                                               -->
<!-- ============================================================= -->

<!-- ============================================================= -->
<!--                    PUBLIC DOCUMENT TYPE DEFINITION            -->
<!--                    TYPICAL INVOCATION                         -->
<!--                                                               -->
<!--  Refer to this file by the following public identifier or an
      appropriate system identifier 
PUBLIC "-//OASIS//ELEMENTS DITA Utilities Domain//EN"
      Delivered as file "utilitiesDomain.mod"                      -->

<!-- ============================================================= -->
<!-- SYSTEM:     Darwin Information Typing Architecture (DITA)     -->
<!--                                                               -->
<!-- PURPOSE:    Declaring the elements and specialization         -->
<!--             attributes for the DITA Utilities Domain          -->
<!--                                                               -->
<!-- ORIGINAL CREATION DATE:                                       -->
<!--             March 2001                                        -->
<!--                                                               -->
<!--             (C) Copyright OASIS Open 2005, 2009.              -->
<!--             (C) Copyright IBM Corporation 2001, 2004.         -->
<!--             All Rights Reserved.                              -->
<!--                                                               -->
<!--  UPDATES:                                                     -->
<!--    2005.11.15 RDA: Updated these comments to match template   -->
<!--    2005.11.15 RDA: Corrected the "Delivered as" system ID     -->
<!--    2006.06.07 RDA: Make universal attributes universal        -->
<!--                      (DITA 1.1 proposal #12)                  -->
<!--    2006.06.14 RDA: Move univ-atts-translate-no into topic.mod -->
<!--    2007.12.01 EK:  Reformatted DTD modules for DITA 1.2       -->
<!--    2008.02.12 RDA: Add text to shape                          -->
<!--    2008.02.13 RDA: Create .content and .attributes entities   -->
<!--    2012.06.10 WEK: Added sort-as element                      -->
<!-- ============================================================= -->


<!-- ============================================================= -->
<!--                   ELEMENT NAME ENTITIES                       -->
<!-- ============================================================= -->

<!ENTITY % imagemap    "imagemap"                                    >
<!ENTITY % area        "area"                                        >
<!ENTITY % shape       "shape"                                       >
<!ENTITY % coords      "coords"                                      >
<!ENTITY % sort-as     "sort-as"                                     >


<!-- ============================================================= -->
<!--                    COMMON ATTLIST SETS                        -->
<!-- ============================================================= -->


<!-- ============================================================= -->
<!--                    ELEMENT DECLARATIONS for IMAGEMAP          -->
<!-- ============================================================= -->


<!--                    LONG NAME: Imagemap                        -->
<!ENTITY % imagemap.content
                       "((%image;), 
                         (%area;)+)"
>
<!ENTITY % imagemap.attributes
             "%display-atts;
              spectitle 
                        CDATA 
                                  #IMPLIED
              %univ-atts; 
              outputclass 
                        CDATA 
                                  #IMPLIED"
>
<!--doc:The imagemap element supports the basic functionality of the HTML client-side image map markup. Imagemap allows you to designate a linkable area or region over an image, allowing a link in that region to display another topic.
Category: Utilities elements-->
<!ELEMENT imagemap    %imagemap.content;>
<!ATTLIST imagemap    %imagemap.attributes;>



<!--                    LONG NAME: Hotspot Area Description        -->
<!ENTITY % area.content
                       "((%shape;), 
                         (%coords;), 
                         (%xref;))"
>
<!ENTITY % area.attributes
             "%univ-atts; 
              outputclass 
                        CDATA 
                                  #IMPLIED"
>
<!--doc:The area element supports the basic functionality of the HTML image map markup.
Category: Utilities elements-->
<!ELEMENT area    %area.content;>
<!ATTLIST area    %area.attributes;>



<!--                    LONG NAME: Shape of the Hotspot            -->
<!ENTITY % shape.content
                       "(#PCDATA |
                         %text;)*
">
<!ENTITY % shape.attributes
             "keyref 
                        CDATA
                                  #IMPLIED
              %univ-atts-translate-no; 
              outputclass 
                        CDATA 
                                  #IMPLIED"
>
<!--doc:The shape element defines the shape of a linkable area in an imagemap.
Category: Utilities elements-->
<!ELEMENT shape    %shape.content;>
<!ATTLIST shape    %shape.attributes;>



<!--                    LONG NAME: Coordinates of the Hotspot      -->
<!ENTITY % coords.content
                       "(%words.cnt;)*"
>
<!ENTITY % coords.attributes
             "keyref
                        CDATA
                                  #IMPLIED
              %univ-atts-translate-no;
              outputclass 
                        CDATA 
                                  #IMPLIED"
>
<!--doc:The coords element specifies the coordinates of the linkable region in an imagemap area.
Category: Utilities elements-->
<!ELEMENT coords    %coords.content;>
<!ATTLIST coords    %coords.attributes;>

<!--                    LONG NAME: Sort key specifier      -->
<!ENTITY % sort-as.content
 "(#PCDATA |
   %text; |
   %keyword;)*"
>
<!ENTITY % sort-as.attributes
             "%univ-atts;
              name 
                        CDATA 
                                  #IMPLIED
              value 
                        CDATA 
                                  #IMPLIED"
>
<!ELEMENT sort-as    %sort-as.content;>
<!ATTLIST sort-as    %sort-as.attributes;>

 

<!-- ============================================================= -->
<!--                    SPECIALIZATION ATTRIBUTE DECLARATIONS      -->
<!-- ============================================================= -->


<!ATTLIST imagemap %global-atts;  class CDATA "+ topic/fig ut-d/imagemap " >
<!ATTLIST area     %global-atts;  class CDATA "+ topic/figgroup ut-d/area ">
<!ATTLIST shape    %global-atts;  class CDATA "+ topic/keyword ut-d/shape ">
<!ATTLIST coords   %global-atts;  class CDATA "+ topic/ph ut-d/coords "    >
<!ATTLIST sort-as  %global-atts;  class CDATA "+ topic/data ut-d/sort-as " >

 
<!-- ================== End Utilities Domain ====================== -->