# CardioGuard - دليل الاختبار والتوثيق الشامل
# CardioGuard - Comprehensive Testing & Documentation Guide

---

## 📱 نظرة عامة على التطبيق | Application Overview

### العربية
**CardioGuard** هو تطبيق أندرويد للكشف المبكر عن أمراض القلب باستخدام تقنيات التعلم الآلي. يستخدم التطبيق ثلاثة نماذج للذكاء الاصطناعي:
- **نموذج KNN**: دقة 82% - الأفضل للكشف المبكر
- **نموذج Naive Bayes**: دقة 82% - أداء متوازن
- **نموذج Decision Tree**: دقة 70% - سهل التفسير

### English
**CardioGuard** is an Android application for early detection of heart disease using machine learning techniques. The application uses three AI models:
- **KNN Model**: 82% accuracy - Best for early detection
- **Naive Bayes Model**: 82% accuracy - Balanced performance
- **Decision Tree Model**: 70% accuracy - Easy to interpret

---

## 🌐 دعم اللغات | Language Support

### نظام اللغات | Language System
التطبيق يدعم لغتين كاملتين:
- **العربية (ar)**: اللغة الافتراضية
- **الإنجليزية (en)**: اللغة الثانوية

#### آلية عمل نظام اللغات | Language System Mechanism

**1. ملفات الموارد | Resource Files**
```
app/src/main/res/
├── values/strings.xml          (العربية - Arabic)
└── values-en/strings.xml       (الإنجليزية - English)
```

**2. اختيار اللغة التلقائي | Automatic Language Selection**
- التطبيق يكتشف لغة النظام تلقائياً
- إذا كان النظام بالعربية → التطبيق بالعربية
- إذا كان النظام بالإنجليزية → التطبيق بالإنجليزية
- أي لغة أخرى → العربية (الافتراضية)

**3. أمثلة الترجمة | Translation Examples**

| المفتاح | العربية | English |
|---------|---------|---------|
| `results_risk_level_title` | مستوى المخاطر | Risk Level |
| `results_risk_low` | منخفض | Low |
| `results_risk_medium` | متوسط | Moderate |
| `results_risk_high` | مرتفع | High |
| `results_model_knn` | نموذج KNN | KNN Model |
| `about_label_accuracy` | الدقة | Accuracy |
| `about_label_precision` | الاستدقاق | Precision |
| `about_label_recall` | الاستدعاء | Recall |

---

## 🎯 كيفية عمل التطبيق | How the Application Works

### 1️⃣ إدخال البيانات الطبية | Medical Data Input

#### الحقول المطلوبة | Required Fields:

| الحقل (عربي) | Field (English) | النطاق | Range | الوصف | Description |
|-------------|----------------|--------|-------|-------|-------------|
| العمر | Age | 1-120 | 1-120 | عمر المريض بالسنوات | Patient age in years |
| الجنس | Sex | ذكر/أنثى | Male/Female | جنس المريض | Patient gender |
| نوع ألم الصدر | Chest Pain Type | 0-3 | 0-3 | نوع الألم في الصدر | Type of chest pain |
| ضغط الدم الانقباضي | Resting Blood Pressure | 90-200 | 90-200 | ضغط الدم عند الراحة | Blood pressure at rest |
| الكوليسترول | Cholesterol | 100-600 | 100-600 | مستوى الكوليسترول | Cholesterol level |
| سكر الدم الصيامي | Fasting Blood Sugar | نعم/لا | Yes/No | > 120 mg/dl | > 120 mg/dl |
| تخطيط القلب | Resting ECG | 0-2 | 0-2 | نتائج تخطيط القلب | ECG results |
| معدل ضربات القلب الأقصى | Max Heart Rate | 60-220 | 60-220 | أقصى معدل للقلب | Maximum heart rate |
| الذبحة الصدرية | Exercise Induced Angina | نعم/لا | Yes/No | ألم عند التمرين | Pain during exercise |
| انخفاض ST | ST Depression (Oldpeak) | 0.0-6.2 | 0.0-6.2 | انخفاض موجة ST | ST wave depression |
| ميل ST | Slope of ST | 0-2 | 0-2 | ميل موجة ST | ST segment slope |
| عدد الأوعية الرئيسية | Number of Major Vessels | 0-3 | 0-3 | أوعية ملونة | Colored vessels |
| اختبار الثاليوم | Thalassemia Test | 0-3 | 0-3 | نتيجة اختبار الثاليوم | Thalassemia test result |

### 2️⃣ معالجة البيانات | Data Processing

**المسار الكامل | Complete Flow:**

```
المستخدم يدخل البيانات
User enters data
    ↓
التحقق من صحة المدخلات
Input validation
    ↓
إرسال إلى الباك إند (API)
Send to Backend (API)
    ↓
معالجة النماذج الثلاثة
Process three models
    ↓
استلام النتائج
Receive results
    ↓
عرض النتائج بشكل مرئي
Display results visually
```

### 3️⃣ عرض النتائج | Results Display

#### مكونات صفحة النتائج | Results Page Components:

**أ) بطاقة مستوى المخاطر | Risk Level Card**
```
┌─────────────────────────────────┐
│      مستوى المخاطر             │
│      Risk Level                 │
├─────────────────────────────────┤
│                                 │
│        33.92%                   │
│      [  متوسط  ]               │
│      [ Moderate ]               │
│                                 │
│   احتمالية الإصابة بمرض القلب   │
│   Probability of heart disease  │
└─────────────────────────────────┘
```

**ب) مقارنة النماذج | Models Comparison**
```
┌─────────────────────────────────┐
│  نموذج KNN | KNN Model          │
│  نسبة الثقة: 66.84%            │
│  Confidence: 66.84%             │
│  الدقة: 82%                     │
│  Accuracy: 82%                  │
├─────────────────────────────────┤
│  نموذج Naive Bayes             │
│  Naive Bayes Model              │
│  نسبة الثقة: 27.90%            │
│  Confidence: 27.90%             │
│  الدقة: 82%                     │
│  Accuracy: 82%                  │
├─────────────────────────────────┤
│  نموذج Decision Tree           │
│  Decision Tree Model            │
│  نسبة الثقة: 2.39%             │
│  Confidence: 2.39%              │
│  الدقة: 70%                     │
│  Accuracy: 70%                  │
└─────────────────────────────────┘
```

**ج) الرسوم البيانية | Charts**
1. **مخطط شريطي (Bar Chart)**: يعرض نسبة الثقة لكل نموذج
2. **مخطط راداري (Radar Chart)**: يعرض الدقة، الاستدقاق، الاستدعاء، المقياس F1

**د) الاستنتاجات الرئيسية | Key Findings**
- 4 استنتاجات بحثية مهمة
- 4 important research findings

---

## 🎨 نظام الألوان | Color System

### مستويات المخاطر والألوان | Risk Levels and Colors

#### 🟢 منخفض | Low Risk
- **اللون | Color**: أخضر | Green (`#10B981`)
- **النطاق | Range**: 0% - 30%
- **المعنى | Meaning**: احتمالية منخفضة للإصابة | Low probability of disease
- **الإجراء الموصى به | Recommended Action**: متابعة دورية | Regular monitoring

#### 🟠 متوسط | Moderate Risk
- **اللون | Color**: برتقالي | Orange (`#F59E0B`)
- **النطاق | Range**: 30% - 60%
- **المعنى | Meaning**: احتمالية متوسطة للإصابة | Moderate probability of disease
- **الإجراء الموصى به | Recommended Action**: استشارة طبيب | Consult a doctor

#### 🔴 مرتفع | High Risk
- **اللون | Color**: أحمر | Red (`#EF4444`)
- **النطاق | Range**: 60% - 100%
- **المعنى | Meaning**: احتمالية عالية للإصابة | High probability of disease
- **الإجراء الموصى به | Recommended Action**: زيارة فورية للطبيب | Immediate medical attention

### آلية تطبيق الألوان | Color Application Mechanism

```kotlin
// الكود في ResultsActivity.kt
val riskColor = when (riskLevel) {
    "low" -> getColor(R.color.secondary)      // 🟢 أخضر
    "moderate", "medium" -> getColor(R.color.accent)  // 🟠 برتقالي
    "high" -> getColor(R.color.error)         // 🔴 أحمر
}
```

---

## 🧪 حالات الاختبار التفصيلية | Detailed Test Cases

### اختبار 1: مخاطر منخفضة 🟢 | Test 1: Low Risk 🟢

#### بيانات المدخلات | Input Data:
```
العمر | Age: 30
الجنس | Sex: أنثى | Female
نوع ألم الصدر | Chest Pain: عديم الألم (0) | Asymptomatic (0)
ضغط الدم | Blood Pressure: 110 mmHg
الكوليسترول | Cholesterol: 180 mg/dl
سكر الدم الصيامي | Fasting Blood Sugar: لا | No
تخطيط القلب | Resting ECG: طبيعي (0) | Normal (0)
معدل القلب الأقصى | Max Heart Rate: 170 bpm
الذبحة الصدرية | Exercise Angina: لا | No
انخفاض ST | Oldpeak: 0.0
ميل ST | Slope: صاعد (0) | Upsloping (0)
عدد الأوعية | CA: 0
اختبار الثاليوم | Thal: طبيعي (3) | Normal (3)
```

#### النتائج المتوقعة | Expected Results:
```
✅ مستوى المخاطر: منخفض 🟢
✅ Risk Level: Low 🟢
✅ النسبة: 10% - 25%
✅ Percentage: 10% - 25%
✅ اللون: أخضر
✅ Color: Green
✅ النص: "منخفض" (عربي) / "Low" (English)
```

#### لقطة شاشة متوقعة | Expected Screenshot:
```
┌─────────────────────────┐
│   مستوى المخاطر        │
│   Risk Level            │
├─────────────────────────┤
│      15.23% 🟢          │
│     [  منخفض  ]        │
│     [   Low    ]        │
└─────────────────────────┘
```

---

### اختبار 2: مخاطر متوسطة 🟠 | Test 2: Moderate Risk 🟠

#### بيانات المدخلات | Input Data:
```
العمر | Age: 50
الجنس | Sex: ذكر | Male
نوع ألم الصدر | Chest Pain: ألم نموذجي (1) | Typical Angina (1)
ضغط الدم | Blood Pressure: 130 mmHg
الكوليسترول | Cholesterol: 240 mg/dl
سكر الدم الصيامي | Fasting Blood Sugar: لا | No
تخطيط القلب | Resting ECG: طبيعي (0) | Normal (0)
معدل القلب الأقصى | Max Heart Rate: 150 bpm
الذبحة الصدرية | Exercise Angina: لا | No
انخفاض ST | Oldpeak: 1.0
ميل ST | Slope: مستوي (1) | Flat (1)
عدد الأوعية | CA: 1
اختبار الثاليوم | Thal: عيب ثابت (6) | Fixed Defect (6)
```

#### النتائج المتوقعة | Expected Results:
```
✅ مستوى المخاطر: متوسط 🟠
✅ Risk Level: Moderate 🟠
✅ النسبة: 35% - 55%
✅ Percentage: 35% - 55%
✅ اللون: برتقالي
✅ Color: Orange
✅ النص: "متوسط" (عربي) / "Moderate" (English)
```

#### لقطة شاشة متوقعة | Expected Screenshot:
```
┌─────────────────────────┐
│   مستوى المخاطر        │
│   Risk Level            │
├─────────────────────────┤
│      45.67% 🟠          │
│     [  متوسط  ]        │
│     [ Moderate ]        │
└─────────────────────────┘
```

---

### اختبار 3: مخاطر مرتفعة 🔴 | Test 3: High Risk 🔴

#### بيانات المدخلات | Input Data:
```
العمر | Age: 65
الجنس | Sex: ذكر | Male
نوع ألم الصدر | Chest Pain: ألم نموذجي (1) | Typical Angina (1)
ضغط الدم | Blood Pressure: 160 mmHg
الكوليسترول | Cholesterol: 300 mg/dl
سكر الدم الصيامي | Fasting Blood Sugar: نعم | Yes
تخطيط القلب | Resting ECG: تضخم البطين (2) | LV Hypertrophy (2)
معدل القلب الأقصى | Max Heart Rate: 120 bpm
الذبحة الصدرية | Exercise Angina: نعم | Yes
انخفاض ST | Oldpeak: 3.5
ميل ST | Slope: هابط (2) | Downsloping (2)
عدد الأوعية | CA: 3
اختبار الثاليوم | Thal: عيب قابل للعكس (7) | Reversible Defect (7)
```

#### النتائج المتوقعة | Expected Results:
```
✅ مستوى المخاطر: مرتفع 🔴
✅ Risk Level: High 🔴
✅ النسبة: 70% - 95%
✅ Percentage: 70% - 95%
✅ اللون: أحمر
✅ Color: Red
✅ النص: "مرتفع" (عربي) / "High" (English)
```

#### لقطة شاشة متوقعة | Expected Screenshot:
```
┌─────────────────────────┐
│   مستوى المخاطر        │
│   Risk Level            │
├─────────────────────────┤
│      82.34% 🔴          │
│     [  مرتفع  ]        │
│     [   High   ]        │
└─────────────────────────┘
```

---

## 📊 أمثلة النسب الفعلية | Actual Percentage Examples

### من نتائج الاختبار الحقيقية | From Real Test Results:

#### مثال 1: حالة منخفضة الخطورة | Example 1: Low Risk Case
```json
{
  "ensemble": {
    "riskScore": 0.1237,
    "riskLevel": "low",
    "prediction": false
  },
  "knn": {
    "confidence": 23.45,
    "accuracy": 82
  },
  "naiveBayes": {
    "confidence": 18.90,
    "accuracy": 82
  },
  "decisionTree": {
    "confidence": 5.67,
    "accuracy": 70
  }
}
```
**عرض التطبيق | App Display:**
- مستوى المخاطر: **12.37%** 🟢 **منخفض**
- Risk Level: **12.37%** 🟢 **Low**

---

#### مثال 2: حالة متوسطة الخطورة | Example 2: Moderate Risk Case
```json
{
  "ensemble": {
    "riskScore": 0.4523,
    "riskLevel": "moderate",
    "prediction": false
  },
  "knn": {
    "confidence": 55.84,
    "accuracy": 82
  },
  "naiveBayes": {
    "confidence": 48.23,
    "accuracy": 82
  },
  "decisionTree": {
    "confidence": 32.56,
    "accuracy": 70
  }
}
```
**عرض التطبيق | App Display:**
- مستوى المخاطر: **45.23%** 🟠 **متوسط**
- Risk Level: **45.23%** 🟠 **Moderate**

---

#### مثال 3: حالة عالية الخطورة | Example 3: High Risk Case
```json
{
  "ensemble": {
    "riskScore": 0.8567,
    "riskLevel": "high",
    "prediction": true
  },
  "knn": {
    "confidence": 89.12,
    "accuracy": 82
  },
  "naiveBayes": {
    "confidence": 85.45,
    "accuracy": 82
  },
  "decisionTree": {
    "confidence": 78.90,
    "accuracy": 70
  }
}
```
**عرض التطبيق | App Display:**
- مستوى المخاطر: **85.67%** 🔴 **مرتفع**
- Risk Level: **85.67%** 🔴 **High**

---

## 🔍 التحقق من صحة العمل | Verification Checklist

### قائمة التحقق الشاملة | Complete Checklist:

#### ✅ الوظائف الأساسية | Basic Functions
- [ ] إدخال البيانات يعمل بشكل صحيح
- [ ] Data input works correctly
- [ ] التحقق من صحة المدخلات يعمل
- [ ] Input validation works
- [ ] الاتصال بالـ API يعمل
- [ ] API connection works
- [ ] استلام النتائج يعمل
- [ ] Results reception works

#### ✅ عرض النتائج | Results Display
- [ ] نسبة المخاطر تُعرض بشكل صحيح (ليست بالآلاف)
- [ ] Risk percentage displays correctly (not in thousands)
- [ ] اللون يتغير حسب مستوى الخطر
- [ ] Color changes based on risk level
  - [ ] 🟢 أخضر للمنخفض | Green for low
  - [ ] 🟠 برتقالي للمتوسط | Orange for moderate
  - [ ] 🔴 أحمر للمرتفع | Red for high
- [ ] النص يُعرض بلغة النظام
- [ ] Text displays in system language
- [ ] مقارنة النماذج تُعرض بشكل صحيح
- [ ] Models comparison displays correctly
- [ ] الرسوم البيانية تعمل
- [ ] Charts work properly

#### ✅ دعم اللغات | Language Support
- [ ] النصوص العربية تظهر بشكل صحيح
- [ ] Arabic text displays correctly
- [ ] النصوص الإنجليزية تظهر بشكل صحيح
- [ ] English text displays correctly
- [ ] أسماء النماذج مترجمة
- [ ] Model names are translated
- [ ] تسميات الرسوم البيانية مترجمة
- [ ] Chart labels are translated

#### ✅ الدقة | Accuracy
- [ ] النسب المئوية معقولة (0-100%)
- [ ] Percentages are reasonable (0-100%)
- [ ] مستوى الخطر يتوافق مع النسبة
- [ ] Risk level matches the percentage
- [ ] نسب الثقة للنماذج صحيحة
- [ ] Model confidence values are correct

---

## 🛠️ معلومات تقنية | Technical Information

### البنية المعمارية | Architecture

```
┌─────────────────────────────────────────┐
│          Android Application            │
│        (Kotlin + XML + Material)        │
└─────────────────────┬───────────────────┘
                      │
                      ↓
┌─────────────────────────────────────────┐
│          API Layer (Retrofit)           │
│      (HTTP/HTTPS Communication)         │
└─────────────────────┬───────────────────┘
                      │
                      ↓
┌─────────────────────────────────────────┐
│       Backend API (Flask/Python)        │
│     (Machine Learning Models)           │
└─────────────────────┬───────────────────┘
                      │
                      ↓
┌─────────────────────────────────────────┐
│         ML Models Processing            │
│  (KNN, Naive Bayes, Decision Tree)      │
└─────────────────────────────────────────┘
```

### ملفات المشروع الرئيسية | Main Project Files

```
HeartDiseaseApp/
├── app/src/main/
│   ├── java/com/cardioguard/heartdisease/
│   │   ├── MainActivity.kt                    # الشاشة الرئيسية
│   │   ├── RiskCalculatorActivity.kt          # شاشة إدخال البيانات
│   │   ├── ResultsActivity.kt                 # شاشة النتائج
│   │   ├── AboutActivity.kt                   # شاشة حول التطبيق
│   │   ├── models/
│   │   │   ├── PredictionRequest.kt           # بيانات الطلب
│   │   │   └── PredictionResponse.kt          # بيانات الاستجابة
│   │   ├── network/
│   │   │   └── RetrofitClient.kt              # الاتصال بالـ API
│   │   └── viewmodels/
│   │       └── PredictionViewModel.kt         # معالجة البيانات
│   └── res/
│       ├── values/
│       │   ├── strings.xml                    # النصوص العربية
│       │   ├── colors.xml                     # الألوان
│       │   └── styles.xml                     # الأنماط
│       ├── values-en/
│       │   └── strings.xml                    # النصوص الإنجليزية
│       └── layout/
│           ├── activity_main.xml              # تصميم الشاشة الرئيسية
│           ├── activity_risk_calculator.xml   # تصميم شاشة الإدخال
│           ├── activity_results.xml           # تصميم شاشة النتائج
│           └── activity_about.xml             # تصميم شاشة حول
```

---

## 📝 ملاحظات مهمة | Important Notes

### العربية

1. **دقة النتائج**: هذا النظام للأغراض البحثية فقط ولا يحل محل الاستشارة الطبية المتخصصة.

2. **متطلبات الاتصال**: يحتاج التطبيق إلى اتصال بالإنترنت للتواصل مع الباك إند.

3. **خصوصية البيانات**: لا يتم حفظ البيانات الطبية على الجهاز، تُرسل مباشرة إلى الخادم ثم تُحذف.

4. **توافق النظام**: يعمل التطبيق على أندرويد 5.0 (Lollipop) وما فوق.

5. **حجم التطبيق**: حوالي 15-20 ميجابايت.

### English

1. **Result Accuracy**: This system is for research purposes only and does not replace professional medical consultation.

2. **Connection Requirements**: The application needs internet connection to communicate with the backend.

3. **Data Privacy**: Medical data is not stored on the device, it's sent directly to the server then deleted.

4. **System Compatibility**: The application works on Android 5.0 (Lollipop) and above.

5. **Application Size**: Approximately 15-20 megabytes.

---

## 🎓 معلومات البحث | Research Information

### العربية
**الباحث**: [اسم الطالب]
**الإشراف**: [أسماء المشرفين]
**الجامعة**: الجامعة الافتراضية السورية
**الوزارة**: وزارة التعليم العالي

**البريد الإلكتروني للباحث**: research@example.com
**البريد الإلكتروني للمشرف الأول**: supervisor1@example.com
**البريد الإلكتروني للمشرف الثاني**: supervisor2@example.com

### English
**Researcher**: [Student Name]
**Supervisors**: [Supervisor Names]
**University**: Syrian Virtual University
**Ministry**: Ministry of Higher Education

**Researcher Email**: research@example.com
**First Supervisor Email**: supervisor1@example.com
**Second Supervisor Email**: supervisor2@example.com

---

## 📞 الدعم الفني | Technical Support

### للاستفسارات التقنية | For Technical Inquiries:
- **البريد الإلكتروني | Email**: support@cardioguard.example.com
- **الموقع | Website**: https://cardioguard.example.com

### للإبلاغ عن مشكلة | To Report an Issue:
يرجى إرسال رسالة تحتوي على:
Please send a message containing:
1. وصف المشكلة | Problem description
2. لقطة شاشة | Screenshot
3. إصدار التطبيق | App version
4. إصدار أندرويد | Android version

---

## 📅 سجل الإصدارات | Version History

### الإصدار 1.0.0 (2026-02-08)
- ✅ إطلاق النسخة الأولى
- ✅ First release
- ✅ دعم اللغتين العربية والإنجليزية
- ✅ Arabic and English language support
- ✅ ثلاثة نماذج للتعلم الآلي
- ✅ Three machine learning models
- ✅ واجهة مستخدم محسّنة
- ✅ Enhanced user interface
- ✅ نظام ألوان ديناميكي
- ✅ Dynamic color system
- ✅ رسوم بيانية تفاعلية
- ✅ Interactive charts

---

## 📜 الترخيص | License

© 2026 CardioGuard - جميع الحقوق محفوظة | All Rights Reserved

---

**تاريخ آخر تحديث | Last Updated**: 8 فبراير 2026 | February 8, 2026
**الإصدار | Version**: 1.0.0
