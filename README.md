
<span style="font-family: Times , sans-serif;">

# Document

1. What is Jsoup?
2. How to run the Program
3. what Program using Maven?
4. All the 10 Test


---

# Test Case #1

---
### CodeTest Results (Path)
    path: 

---

# Test Case #2

### **Name of the Test Case:**

    TokenTest

---

### **Goal of the Test Case:**

To test if `hasAttributeIgnoreCase(String key)` correctly identifies whether an element’s attribute exists or not, **ignoring uppercase and lowercase** letters.

---

### **Identify testable functions: **

`Token.java` – **line 199**
![Testcase2](image-2.png)

---

### **Identify parameters, return types, return values, and exceptional behavior:**

| Detail                   | Description                                                                                                   |
| ------------------------ | ------------------------------------------------------------------------------------------------------------- |
| **Parameter type**       | `String key`                                                                                                  |
| **Return type**          | `boolean`                                                                                                     |
| **Return value**         | `true` if the specified attribute exists (case-insensitive), otherwise `false`                                |
| **Exceptional behavior** | None explicitly thrown (unless null handling causes `ValidationException` or NPE depending on implementation) |

---

### **Interface-Based Characteristics**

| Characteristic              | Block 1            | Block 2          | Block 3           |
| --------------------------- | ------------------ | ---------------- | ----------------- |
| **C1 = Attribute presence** | Attribute = 0      | Attribute = 1    | Attribute > 1     |
| **C2 = Key validity**       | Valid key          | Empty key        | Null key          |

**IIdentify (possible) values (interface):**

| Characteristic              | Block 1            | Block 2          | Block 3                             |
| --------------------------- | ------------------ | ---------------- | ----------------------------------- |
| **C1 = Attribute presence** | `<div>`            | `<div id="main">`| `<div id="main" class="content">`   |
| **C2 = Key validity**       | "id"               | “”               | null                                |

---

### **Functionality-Based Characteristics**

| Characteristic                               | Block 1                        | Block 2                             | Block 3             |
| -------------------------------------------- | ------------------------------ | ----------------------------------- | ------------------- |
| **F1 = Case-insensitive attribute matching** | Attribute found with same case | Attribute found with different case | Attribute not found |

**Identify (possible) values (functionality):**

| Characteristic              | Block 1            | Block 2          | Block 3           |
| --------------------------- | ------------------ | ---------------- | ----------------- |
| **F1 = Case-insensitive**   | `<div id=”main”>`  | `<div ID=”main”>`| `<div>`           |

---

### **Combination of Partitions (ECC – Each Choice Coverage)**

**Test requirements(max)=** 3

| **Test ID** | **C1 (Attribute presence)** | **C2 (Key validity)** | **F1 (Case-insensitive)**           |
| ----------- | --------------------------- | --------------------- | ----------------------------------- |
| **TR1**     | Attribute == 0              | Empty key             | Attribute not found                 |
| **TR2**     | Attribute == 1              | Valid key             | Attribute found with same case      |
| **TR3**     | Attribute > 1               | Null key              | Attribute found with different case |

---

### **Test Values and Expected Results**

| **Test ID** | **Blocks (C1, C2, F1)**                                        | **Input (HTML setup, key)**               | **Expected Output**            |
| ----------- | -------------------------------------------------------------- | ----------------------------------------- | ------------------------------ |
| **TR1**     | (Attribute == 0, Empty key, Attribute not found)               | `<div>`, `""`                             | `false`                        |
| **TR2**     | (Attribute == 1, Valid key, Attribute found with same case)    | `<div id="main">`, `"id"`                 | `true`                         |
| **TR3**     | (Attribute > 1, Null key, Attribute found with different case) | `<div id="main" class="content">`, `null` | **Throws ValidationException** |

---
### CodeTest Results (Path)
    path: jsoup/src/test/java/org/jsoup/parser/TokenTest.java

---

# Test Case #3 

### **Name of the Test Case:**

    StringUtilTest

---

### **Goal of the Test Case:**

To check if `startsWithNewline(String string)` correctly identifies whether a string begins **newline character (`'\n'`)**.

---

### **Identify testable functions: **

`StringUtil.java` – **line 168**
![Testcase3](image-3.png)

---

### **Identify parameters, return types, return values, and exceptional behavior:**

| Detail                   | Description                                                            |
| ------------------------ | ---------------------------------------------------------------------- |
| **Parameter type**       | `String string`                                                        |
| **Return type**          | `boolean`                                                              |
| **Return value**         | `true` if the first character is a newline (`'\n'`), otherwise `false` |
| **Exceptional behavior** | None (null input may cause `NullPointerException` if not handled)      |

---

### **Interface-Based Characteristics**

| Characteristic            | Block 1                | Block 2                | Block 3               |
| ------------------------- | ---------------------- | ---------------------- | --------------------- |
| **C1 = String existence** | `Null_String`          | `Not_null`             | –                     |
| **C2 = String length**    | `string.length() == 0` | `string.length() == 1` | `string.length() > 1` |

**IIdentify (possible) values (interface):**

| Characteristic            | Block 1 | Block 2        | Block 3   |
| ------------------------- | ------- | -------------- | --------- |
| **C1 = String existence** | `null`  | `"HelloWorld"` | –         |
| **C2 = String length**    | `""`    | `"h"`          | `"Hello"` |

---

### **Functionality-Based Characteristics**

| Characteristic                          | Block 1 | Block 2 |
| --------------------------------------- | ------- | ------- |
| **F1 = First letter of string == ‘\n’** | `True`  | `False` |

**Identify (possible) values (functionality):**

| Characteristic                          | Block 1    | Block 2 |
| --------------------------------------- | ---------- | --------|
| **F1 = First letter of string == ‘\n’** | `\nHello`  | `Hello` |

---

### **Combination of Partitions (BCC – Each Choice Coverage)**

**Total Test Requirements:** 5  (comes from 1 + ((2 - 1) * 1 + (3 - 1) * 1 + (2-1)*1))

**Base Choices:**

1. `(Not_null, String.length()>1,True)`

| **Test ID**           | **C1 (String existence)** | **C2 (String length)** | **F1 (Starts with '\n')** |
| --------------------- | ------------------------- | ---------------------- | ------------------------- |
| **TR1 (Base Choice)** | Not_null                  | `string.length() > 1`  | True                      |
| **TR2**               | Not_null                  | `string.length() > 1`  | False                     |
| **TR3**               | Null_String               | `string.length() > 1`  | True                      |
| **TR4**               | Not_null                  | `string.length() == 0` | True                      |
| **TR5**               | Not_null                  | `string.length() == 1` | True                      |

---

### **Test Values and Expected Results**

| **Test ID** | **Blocks (C1, C2, F1)**                  | **Input**   | **Expected Output** |
| ----------- | ---------------------------------------- | ----------- | ------------------- |
| **TR1**     | (Not_null, `string.length() > 1`, True)  | `"\nHello"` | `true`              |
| **TR2**     | (Not_null, `string.length() > 1`, False) | `"Hello"`   | `false`             |
| **TR5**     | (Not_null, `string.length() == 1`, True) | `"\n"`      | `true`              |

---
### CodeTest Results (Path)
    path: jsoup/src/test/java/org/jsoup/jsoupForChaiyongTest/StringUtilTest.java

---

# Test Case #4

---

# Test Case #5

---

# Test Case #6

---

# Test Case #7 

### **Name of the Test Case:**
    MyUtilsTest

---

### **Goal of the Test Case:**

This test case checks whether String msg and Object array args are usable or not?(`String msg` shouldn’t be null and the relationship between format specifiers and objects should be appropriate)

---

### **Identify testable functions:**

`ListLinks.java` – **line 50**
![TestCase7](image-1.png)

---

### **Identify parameters, return types, return values, and exceptional behavior:**

| Detail                    | Description                                                                                                  |
| ------------------------- | ------------------------------------------------------------------------------------------------------------ |
| **Parameter types**       | `String msg`, `Object[] args`                                                                                |
| **Return type**           | `void`                                                                                                       |
| **Return value**          | Formatted string printed to the console                                                                      |
| **Exceptional behaviors** | Formatting-related exceptions such as `MissingFormatArgumentException` or `IllegalFormatConversionException` |

---

### **Interface-Based Characteristics**

| Characteristic          | Block 1      | Block 2         | Block 3         |
| ----------------------- | ------------ | --------------- | --------------- |
| **C1= value of String msg** | null string  | empty string    | non-emty string |

**IIdentify (possible) values (interface):**

| Characteristic          | Block 1 | Block 2 | Block 3           |
| ----------------------- | ------- | ------- | ----------------- |
| **C1= value of String msg** | `null`  | `""`    | `"number %d, %d"` |

---

### **Functionality-Based Characteristics**

| **Characteristic**                                                                                                             | **Block 1**                                                            | **Block 2**                                                                      | **Block 3**                                                                |
| ------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------- | -------------------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| **F1 = the relationship between the number of format specifiers in String msg and the number of objects in Object array args** | The number of format specifiers in `msg` < number of objects in `args` | The number of format specifiers in `msg` = number of objects in `args`           | The number of format specifiers in `msg` > number of objects in `args`     |
| **F2 = The relationship between each format specifiers in String msg and each objects in Object array args**                     | Every format specifier in `msg` doesn’t match any object in `args`     | Some format specifiers in `msg` match some objects in `args`, while others don’t | Every format specifier in `msg` matches the corresponding object in `args` |

### **Identify (Possible) Values (Functionality)**

| **Characteristic**                           | **Block 1**                                      | **Block 2**                                  | **Block 3**                              |
| -------------------------------------------- | ------------------------------------------------ | -------------------------------------------- | ---------------------------------------- |
|**F1 = the relationship between the number of format specifiers in String msg and the number of objects in Object array args**  | `(2, 3)`                                         | `(2, 2)`                                     | `(2, 1)`                                 |
| **F2 = The relationship between each format specifiers in String msg and each objects in Object array args** | `("number %d, %d", new Object[] {"one", "two"})` | `("number %d, %d", new Object[] {1, "two"})` | `("number %d, %d", new Object[] {1, 2})` |

---

### **Combination of Partitions PWC (Pair-Wise Coverage)**

**Total Test Requirements:** 9 (3 × 3 combinations)

| **Test ID** | **C1**           | **F1**                                                                                     | **F2**                                                                                                 |
| ----------- | ---------------- | ------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------ |
| **1**     | null string      | the number of format specifiers in String msg < the number of objects in Object array args | every format specifiers in String msg don’t match with every objects in Object array args              |
| **2**     | empty string     | the number of format specifiers in String msg = the number of objects in Object array args | some format specifiers in String msg match with some objects in Object array args, while others don’t. |
| **3**     | non-empty string | the number of format specifiers in String msg > the number of objects in Object array args | every format specifiers in String msg match with every objects in Object array args                    |
| **4**     | null string      | the number of format specifiers in String msg = the number of objects in Object array args | every format specifiers in String msg match with every objects in Object array args                    |
| **5**     | null string      | the number of format specifiers in String msg > the number of objects in Object array args | some format specifiers in String msg match with some objects in Object array args, while others don’t. |
| **6**     | empty string     | the number of format specifiers in String msg < the number of objects in Object array args | every format specifiers in String msg match with every objects in Object array args                    |
| **7**     | empty string     | the number of format specifiers in String msg > the number of objects in Object array args | every format specifiers in String msg don’t match with every objects in Object array args              |
| **8**     | non-empty string | the number of format specifiers in String msg < the number of objects in Object array args | some format specifiers in String msg match with some objects in Object array args, while others don’t. |
| **9**     | non-empty string | the number of format specifiers in String msg = the number of objects in Object array args | every format specifiers in String msg don’t match with every objects in Object array args              |

---

### **Test Values and Expected Results**

| Test ID | Test Values                                     | Expected Output                           |
| ------- | ----------------------------------------------- | ----------------------------------------- |
| **2**   | `("", new Object[]{})`                          | Prints `""` to console                    |
| **3**   | `("number %d, %d", new Object[]{1})`            | Throws `MissingFormatArgumentException`   |
| **6**   | `("", new Object[]{1, 2})`                      | Prints `""` to console                    |
| **8**   | `("number %d, %d", new Object[]{1, "two", 3})`  | Throws `IllegalFormatConversionException` |
| **9**   | `("number %d, %d", new Object[]{"one", "two"})` | Throws `IllegalFormatConversionException` |

---
### CodeTest Results (Path)
    path: jsoup/src/test/java/org/jsoup/jsoupForChaiyongTest/MyUtilsTest.java

---

# Test Case #8

---

# Test Case #9

---

# Test Case #10

### **Name of the Test Case:**

    AttributeCreateFromEncodedTest

---

### **Goal of the Test Case:**

To verify whether the parameters `String unencodedKey` and `String encodedValue` aare usable or not? (String unencodedKey shouldn’t be null or empty and String encodedValue shouldn’t be null)

---

### **Identify testable functions: **

`Attribute.java` – **line 291**
    ![TestCase10](image.png)

---

### **Parameter and Return Details:**

| Detail                   | Description                                                                     |
| ------------------------ | ------------------------------------------------------------------------------- |
| **Parameter types**      | `String unencodedKey`, `String encodedValue`                                    |
| **Return type**          | `Attribute` object                                                              |
| **Return value**         | New `Attribute` object with unencoded key, unencoded value, and `null` parent   |
| **Exceptional behavior** | May throw `ValidationException` or `NullPointerException` when input is invalid |

---

### **Interface-Based Characteristics**

| **Characteristic**                    | **Block 1** | **Block 2**  | **Block 3**      | **Block 4**                         | **Block 5**   |
| ------------------------------------- | ----------- | ------------ | ---------------- | ----------------------------------- | ------------- |
| **C1 = value of String unencodedKey** | null string | empty string | non-empty string | non-empty string with HTML entities | HTML entities |
| **C2 = value of String encodedValue** | null string | empty string | non-empty string | non-empty string with HTML entities | HTML entities |

### **Identify (possible) values (interface):**

| Characteristic        | Block 1 | Block 2 | Block 3   | Block 4                            | Block 5   |
| --------------------- | ------- | ------- | --------- | ---------------------------------- | --------- |
| **C1 = unencodedKey** | `null`  | `""`    | `"class"` | `"quality assurance &amp testing"` | `"&amp;"` |
| **C2 = encodedValue** | `null`  | `""`    | `"class"` | `"quality assurance &amp testing"` | `"&amp;"` |

---

### **Functionality-Based Characteristics**

| Characteristic        | Block 1          | Block 2                           | Block 3                               | Block 4                             |
| --------------------- | ---------------- | --------------------------------- | ------------------------------------- | ----------------------------------- |
| **F1 = Output value** | throws exception | Create attribute with empty value | Create attribute with unencoded value | Create attribute with encoded value |

### **Identify (possible) values (interface):**

| **Characteristic**    | **Block 1**            | **Block 2**                                   | **Block 3**                                        | **Block 4**                                        |
| --------------------- | ---------------------- | --------------------------------------------- | -------------------------------------------------- | -------------------------------------------------- |
| **F1 = Output value** | `NullPointerException` | `Attribute[key=title, value="", parent=null]` | `Attribute[key=title, value="Hello", parent=null]` | `Attribute[key=title, value="Hello", parent=null]` |

---

### **Combination of Partitions MBCC (Multiple Base Choice Coverage)**

**Base Choices:**

1. `(non-empty string, non-empty string, create attribute with unencoded value)`
2. `(null, null, throw exception)`

**Total Test Requirements:** 18 combinations (2 + ((5 - 2) * 2 + (5 - 2) * 2 + (4 - 2) * 2))

---

### **Test Requirements Table**

| Test ID      | C1                                  | C2                                  | F1                                    |
| ------------ | ----------------------------------- | ----------------------------------- | ------------------------------------- |
| 1 (Base 1) | non-empty string                    | non-empty string                    | Create attribute with unencoded value |
| 2 (Base 2) | null                                | null                                | throw exception                       |
| 3          | non-empty string                    | non-empty string                    | Create attribute with unencoded value |
| 4          | non-empty string                    | non-empty string                    | Create attribute with unencoded value |
| 5          | empty string                        | non-empty string                    | Create attribute with unencoded value |
| 6          | non-empty string with HTML entities | non-empty string                    | Create attribute with unencoded value |
| 7          | HTML entities                       | non-empty string                    | Create attribute with unencoded value |
| 8          | non-empty string                    | empty string                        | Create attribute with empty value     |
| 9          | non-empty string                    | non-empty string with HTML entities | Create attribute with unencoded value |
| 10         | non-empty string                    | HTML entities                       | Create attribute with unencoded value |
| 11         | null                                | empty string                        | Create attribute with empty value     |
| 12         | null                                | non-empty string with HTML entities | Create attribute with unencoded value |
| 13         | null                                | HTML entities                       | Create attribute with unencoded value |
| 14         | empty string                        | null                                | throw exception                       |
| 15         | non-empty string with HTML entities | null                                | throw exception                       |
| 16         | HTML entities                       | null                                | throw exception                       |
| 17         | null                                | null                                | Create attribute with empty value     |
| 18         | null                                | null                                | Create attribute with unencoded value |

---

### **Test Values and Expected Results**


| Test ID | Test Values                                     | Expected Output                                                               |
| ------- | ----------------------------------------------- | ----------------------------------------------------------------------------- |
| **1**   | (`"title"`, `"Hello"`)                          | `Attribute[key=title, value="Hello", parent=null]`                            |
| **2**   | (`null`, `null`)                                | Throws `ValidationException`                                                  |
| **5**   | (`""`, `"quality assurance"`)                   | `Attribute[key="", value="quality assurance", parent=null]`                   |
| **6**   | (`"quality assurance &amp testing"`, `"Hello"`) | `Attribute[key="quality assurance &amp testing", value="Hello", parent=null]` |
| **7**   | (`"&amp;"`, `"Hello"`)                          | `Attribute[key="&amp;", value="Hello", parent=null]`                          |
| **11**  | (`null`, `""`)                                  | `Attribute[key=null, value="", parent=null]`                                  |
| **12**  | (`null`, `"quality assurance &amp testing"`)    | Throws `ValidationException`                                                  |
| **13**  | (`null`, `"&amp;"`)                             | Throws `ValidationException`                                                  |
| **14**  | (`""`, `null`)                                  | Throws `ValidationException`                                                  |
| **15**  | (`"quality assurance &amp testing"`, `null`)    | Throws `ValidationException`                                                  |
| **16**  | (`"&amp;"`, `null`)                             | Throws `ValidationException`                                                  |

---
### CodeTest Results (Path)
    path:jsoup/src/test/java/org/jsoup/jsoupForChaiyongTest/AttributeCreateFromEncodedTest.java

</span>