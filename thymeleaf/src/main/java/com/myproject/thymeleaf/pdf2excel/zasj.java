package com.myproject.thymeleaf.pdf2excel;
//
//import com.aspose.cells.b.a.b.za;
//import com.aspose.cells.b.a.b.zc;
//import com.aspose.cells.b.a.b.zh;
//import com.aspose.cells.b.a.b.zn;
//import com.aspose.cells.b.a.b.zv;
//

import lombok.Data;

import java.io.Serializable;

@Data
class zasj implements Serializable {


//  protected zaic a = null;
//
//  protected zaic b = null;
//
//  protected zaic c = null;
//
//  protected zaic d;
//
//  protected zn e;
//
//  protected Color f = Color.fromArgb(0, 0, 0);
//
//  protected za g = null;
//
//  protected boolean h = false;
//
//  protected boolean i = false;
//
//  protected boolean j;
//
//  protected int k = 2;
//
//  protected boolean l;
//
//  public void a(Color paramColor1, Color paramColor2, int paramInt) {
//    true;
//  }
//
//  public void a(zc paramzc, Color paramColor, int paramInt) {
//    zbss zbss = new zbss();
//    zbss.a(paramzc, paramColor, paramInt);
//    this.a = (zaic)zbss;
//    zavl zavl = new zavl();
//    zavl.a(paramzc, paramColor, paramInt);
//    this.d = (zaic)zavl;
//  }
//
//  public void a() {
//    this.c = null;
//    this.b = null;
//  }
//
//  public void a(boolean paramBoolean) {
//    this.h = paramBoolean;
//  }
//
//  public void a(Color paramColor, int paramInt, zn paramzn) {
//    zuv zuv1 = new zuv();
//    zuv1.a(Color.fromArgb(0, 0, 0, 0), paramColor, paramInt, paramzn.a(), paramzn.b());
//    this.f = paramColor;
//    zuv zuv2 = new zuv();
//    zuv2.a(Color.fromArgb(paramColor.getA() & 0xFF, 255, 255), Color.fromArgb(0, 0, 0, 0), 0, paramzn.a(), paramzn.b());
//    this.c = (zaic)zuv2;
//    this.e = paramzn;
//    this.b = (zaic)zuv1;
//    this.l = true;
//    this.i = false;
//    this.k = paramInt;
//  }
//
//  public boolean a(zn paramzn, zh paramzh, int paramInt1, int paramInt2, String paramString, zn paramzn1, zv paramzv) {
//    if (this.h && this.b != null)
//      this.b.a(paramzn, paramzh, paramInt1, paramInt2, paramString, new zn(paramzn1.a() + this.e.a(), paramzn1.b() + this.e.b()), paramzv);
//    if (this.j)
//      this.d.a(paramzn, paramzh, paramInt1, paramInt2, paramString, new zn(paramzn1.a() + this.e.a(), paramzn1.b() + this.e.b()), paramzv);
//    return (this.a != null) ? this.a.a(paramzn, paramzh, paramInt1, paramInt2, paramString, paramzn1, paramzv) : false;
//  }
//
//  public boolean a(zn paramzn, zh paramzh, int paramInt1, int paramInt2, String paramString, zn paramzn1, zv paramzv, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {
//    float f1 = 0.0F;
//    float f2 = 0.0F;
//    float[] arrayOfFloat1 = { f1 };
//    float[] arrayOfFloat2 = { f2 };
//    boolean bool = this.a.a(paramzn, paramzh, paramInt1, paramInt2, paramString, paramzn1, paramzv, arrayOfFloat1, arrayOfFloat2);
//    f1 = arrayOfFloat1[0];
//    f2 = arrayOfFloat2[0];
//    if (!bool)
//      return false;
//    float f3 = 0.0F;
//    float f4 = 0.0F;
//    if (this.h) {
//      float[] arrayOfFloat3 = { f3 };
//      float[] arrayOfFloat4 = { f4 };
//      bool = this.b.a(paramzn, paramzh, paramInt1, paramInt2, paramString, paramzn1, paramzv, arrayOfFloat3, arrayOfFloat4);
//      f3 = arrayOfFloat3[0];
//      f4 = arrayOfFloat4[0];
//      if (bool) {
//        float f5 = 0.0F;
//        float f6 = 0.0F;
//        float[] arrayOfFloat5 = { f5 };
//        float[] arrayOfFloat6 = { f6 };
//        bool = zabw.a(paramzn, this.e.a(), this.e.b(), arrayOfFloat5, arrayOfFloat6);
//        f5 = arrayOfFloat5[0];
//        f6 = arrayOfFloat6[0];
//        if (bool) {
//          f3 += f5;
//          f4 += f6;
//        }
//      } else {
//        return false;
//      }
//    }
//    if (f1 > f3 || f2 > f4) {
//      paramArrayOffloat1[0] = f1;
//      paramArrayOffloat2[0] = f2;
//    } else {
//      paramArrayOffloat1[0] = f3;
//      paramArrayOffloat2[0] = f4;
//    }
//    return true;
//  }

    String name;
    Integer age;

    public static void main(String[] args) {

    }
}
