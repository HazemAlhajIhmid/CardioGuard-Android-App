# CardioGuard Android Application

<div dir="rtl">

## 📱 تطبيق CardioGuard للكشف المبكر عن أمراض القلب

## 🔗 روابط المشاريع

<div align="center">

| المشروع | التقنية | الرابط | الحالة |
|:-------:|:-------:|:------:|:------:|
| 🏠 Hub | Documentation | **[CardioGuard-Hub](https://github.com/HazemAlhajIhmid/CardioGuard-Hub)** | 📚 Docs |
| 🌐 Frontend | SvelteKit | **[Web App](https://github.com/HazemAlhajIhmid/Master-Thesis--CardioGuard---Early-Detection-of-Heart-Disease-System)** | ✅ Live |
| 🖥️ Backend | ASP.NET Core | **[Backend API](https://github.com/HazemAlhajIhmid/CardioGuard-Backend-API)** | ✅ Live |
| 📱 Android | Kotlin | **[Android App](https://github.com/HazemAlhajIhmid/CardioGuard-Android-App)** | ✅ Live |

**🌐 Live Demo:** [heart-disease-detection.vercel.app](https://heart-disease-detection.vercel.app/)  
**📥 Download APK:** [CardioGuard v1.2.1](https://github.com/HazemAlhajIhmid/CardioGuard-Android-App/releases/tag/V1.2.1)

</div>

---

### نظرة عامة
**CardioGuard** هو تطبيق أندرويد ذكي يستخدم تقنيات التعلم الآلي للكشف المبكر عن أمراض القلب. يعتمد التطبيق على ثلاثة نماذج للذكاء الاصطناعي لتقديم تقييم دقيق لمخاطر الإصابة بأمراض القلب.

### المميزات الرئيسية ✨
- 🤖 **ثلاثة نماذج للذكاء الاصطناعي**: KNN، Naive Bayes، Decision Tree
- 🌐 **دعم لغتين كامل**: العربية والإنجليزية
- 🎨 **واجهة مستخدم حديثة**: Material Design 3
- 📊 **رسوم بيانية تفاعلية**: مخططات شريطية ورادارية
- 🎯 **نظام ألوان ذكي**: يتغير حسب مستوى الخطر
- 📱 **تصميم متجاوب**: يعمل على جميع أحجام الشاشات

### نماذج التعلم الآلي 🧠

| النموذج | الدقة | الوصف |
|---------|------|-------|
| **KNN** | 82% | الأفضل للكشف المبكر - أعلى معدل استرجاع (94%) |
| **Naive Bayes** | 82% | أداء متوازن وسريع - مثالي للتطبيقات الفورية |
| **Decision Tree** | 70% | سهل التفسير - يحتاج إلى تحسين |

### نظام الألوان 🎨

| المستوى | اللون | النطاق | الوصف |
|---------|-------|--------|-------|
| **منخفض** | 🟢 أخضر | 0% - 30% | احتمالية منخفضة للإصابة |
| **متوسط** | 🟠 برتقالي | 30% - 60% | احتمالية متوسطة للإصابة |
| **مرتفع** | 🔴 أحمر | 60% - 100% | احتمالية عالية للإصابة |

### متطلبات النظام 📋
- **نظام التشغيل**: Android 5.0 (Lollipop) أو أحدث
- **الاتصال بالإنترنت**: مطلوب
- **المساحة**: 20 ميجابايت تقريباً
- **الأذونات**: الإنترنت فقط

### التقنيات المستخدمة 🛠️
- **اللغة**: Kotlin
- **واجهة المستخدم**: XML + Material Design Components
- **الاتصال بالشبكة**: Retrofit 2
- **الرسوم البيانية**: MPAndroidChart
- **معمارية التطبيق**: MVVM (Model-View-ViewModel)
- **إدارة الحالة**: LiveData + ViewModel

### البنية المعمارية 🏗️

```
📁 app/src/main/
├── 📁 java/com/cardioguard/heartdisease/
│   ├── 📄 MainActivity.kt                  # الشاشة الرئيسية
│   ├── 📄 RiskCalculatorActivity.kt        # شاشة حساب المخاطر
│   ├── 📄 ResultsActivity.kt               # شاشة النتائج
│   ├── 📄 AboutActivity.kt                 # شاشة حول التطبيق
│   ├── 📁 models/
│   │   ├── 📄 PredictionRequest.kt         # نموذج الطلب
│   │   ├── 📄 PredictionResponse.kt        # نموذج الاستجابة
│   │   ├── 📄 ModelResult.kt               # بيانات النموذج
│   │   └── 📄 EnsembleResult.kt            # النتيجة النهائية
│   ├── 📁 network/
│   │   ├── 📄 ApiService.kt                # واجهة API
│   │   └── 📄 RetrofitClient.kt            # عميل HTTP
│   └── 📁 viewmodels/
│       └── 📄 PredictionViewModel.kt       # معالجة البيانات
└── 📁 res/
    ├── 📁 values/
    │   ├── 📄 strings.xml                  # النصوص العربية
    │   ├── 📄 colors.xml                   # الألوان
    │   └── 📄 styles.xml                   # الأنماط
    ├── 📁 values-en/
    │   └── 📄 strings.xml                  # النصوص الإنجليزية
    ├── 📁 layout/
    │   ├── 📄 activity_main.xml
    │   ├── 📄 activity_risk_calculator.xml
    │   ├── 📄 activity_results.xml
    │   └── 📄 activity_about.xml
    └── 📁 drawable/
        └── (الرسومات والأيقونات)
```

### كيفية البناء والتشغيل 🚀

#### المتطلبات الأساسية:
- Android Studio Arctic Fox أو أحدث
- JDK 11 أو أحدث
- Gradle 8.14

#### خطوات البناء:

1. **استنساخ المشروع**:
```bash
git clone [repository-url]
cd heart-disease-detection/android/HeartDiseaseApp
```

2. **فتح المشروع في Android Studio**:
```
File → Open → اختر مجلد HeartDiseaseApp
```

3. **بناء المشروع**:
```bash
./gradlew assembleDebug
```

4. **تشغيل التطبيق**:
```bash
./gradlew installDebug
```

### ملفات التوثيق 📚

| الملف | الوصف |
|-------|-------|
| [TESTING_DOCUMENTATION.md](TESTING_DOCUMENTATION.md) | توثيق شامل بالعربية والإنجليزية |
| [QUICK_TEST_GUIDE_AR.md](QUICK_TEST_GUIDE_AR.md) | دليل الاختبار السريع بالعربية |
| README.md | هذا الملف |

### حالات الاختبار السريعة 🧪

#### 🟢 مخاطر منخفضة:
```
عمر: 30، جنس: أنثى، ضغط الدم: 110، كوليسترول: 180
النتيجة المتوقعة: 10-25% 🟢 منخفض
```

#### 🟠 مخاطر متوسطة:
```
عمر: 50، جنس: ذكر، ضغط الدم: 130، كوليسترول: 240
النتيجة المتوقعة: 35-55% 🟠 متوسط
```

#### 🔴 مخاطر مرتفعة:
```
عمر: 65، جنس: ذكر، ضغط الدم: 160، كوليسترول: 300
النتيجة المتوقعة: 70-95% 🔴 مرتفع
```

### الشاشات الرئيسية 📸

```
الشاشة الرئيسية         حاسبة المخاطر           النتائج            حول التطبيق
     ┌────┐                ┌────┐               ┌────┐              ┌────┐
     │ 🏠 │    ────────>   │ 📝 │   ────────>  │ 📊 │   ────────>  │ ℹ️  │
     └────┘                └────┘               └────┘              └────┘
   - 3 نماذج ML           - إدخال البيانات      - مستوى المخاطر    - معلومات الفريق
   - دقة النماذج          - 13 حقل طبي          - مقارنة النماذج    - الجامعة
   - المميزات             - التحقق التلقائي      - رسوم بيانية       - الاستنتاجات
```

### معلومات البحث 🎓

**الباحث**: hazem alhajIhmid  
**الإشراف**:  Dr. George Anwar Karraz   and  Dr. Majeda Al-Bakour
**الجامعة**: الجامعة الافتراضية السورية  
**الوزارة**: وزارة التعليم العالي  

**البريد الإلكتروني**:
- الباحث: Hazem_82763@svuonline.org
- المشرف الأول: T_gkarraz@svuonline.org
- المشرف الثاني: T_mbakour@svuonline.org

### الإصدارات 📅

#### v1.0.0 (2026-02-08)
- ✅ إطلاق النسخة الأولى
- ✅ دعم اللغتين العربية والإنجليزية
- ✅ ثلاثة نماذج للتعلم الآلي
- ✅ واجهة مستخدم محسّنة
- ✅ نظام ألوان ديناميكي حسب مستوى الخطر
- ✅ رسوم بيانية تفاعلية (Bar & Radar Charts)
- ✅ 4 استنتاجات بحثية رئيسية

### المساهمة 🤝
هذا المشروع البحثي مفتوح للمساهمات. يرجى:
1. عمل Fork للمشروع
2. إنشاء Branch جديد (`git checkout -b feature/amazing-feature`)
3. Commit التغييرات (`git commit -m 'Add amazing feature'`)
4. Push إلى Branch (`git push origin feature/amazing-feature`)
5. فتح Pull Request

### المشاكل المعروفة والحلول 🔧

| المشكلة | الحل |
|---------|------|
| النسب بالآلاف (3392%) | تم إصلاحها - الكود يتحقق من القيمة |
| الألوان دائماً خضراء | تم إصلاحها - دعم "moderate" و "medium" |
| النصوص بالإنجليزية في النسخة العربية | تم إصلاحها - جميع النصوص مترجمة |
| الاستنتاجات لا تظهر | تم إصلاحها - إضافة 4 استنتاجات |

### الترخيص 📜
© 2026 CardioGuard - جميع الحقوق محفوظة

هذا المشروع للأغراض البحثية فقط ولا يحل محل الاستشارة الطبية المتخصصة.

### الدعم الفني 📞
- **البريد الإلكتروني**: hazem1990alhaj@gmail.com
- **الموقع**: https://master-thesis-cardio-guard-early-de.vercel.app

### ملاحظات مهمة ⚠️
1. **هذا النظام للأغراض البحثية فقط**
2. **لا يحل محل الاستشارة الطبية المتخصصة**
3. **يحتاج اتصال بالإنترنت للعمل**
4. **لا يتم حفظ البيانات الطبية على الجهاز**

---

### الشكر والتقدير 🙏
- الجامعة الافتراضية السورية
- وزارة التعليم العالي
- المشرفين الأكاديميين
- مجتمع المطورين مفتوح المصدر

---

**تاريخ آخر تحديث**: 8 فبراير 2026  
**الإصدار**: 1.0.0  
**الحالة**: ✅ Active Development

</div>

---

## English Version

### Overview
**CardioGuard** is an intelligent Android application that uses machine learning techniques for early detection of heart disease. The application relies on three AI models to provide accurate risk assessment.

### Key Features
- 🤖 Three AI models (KNN, Naive Bayes, Decision Tree)
- 🌐 Full bilingual support (Arabic & English)
- 🎨 Modern UI with Material Design 3
- 📊 Interactive charts (Bar & Radar)
- 🎯 Smart color system based on risk level
- 📱 Responsive design for all screen sizes

### Quick Start
```bash
# Clone the repository
git clone [repository-url]

# Build the project
cd android/HeartDiseaseApp
./gradlew assembleDebug

# Run the app
./gradlew installDebug
```

### Documentation
- [Full Testing Documentation](TESTING_DOCUMENTATION.md) - Complete guide in Arabic & English
- [Quick Test Guide (Arabic)](QUICK_TEST_GUIDE_AR.md) - Quick reference for testing

### License
© 2026 CardioGuard - All Rights Reserved

**For Research Purposes Only**

---

Made with ❤️ by Syrian Virtual University Students
