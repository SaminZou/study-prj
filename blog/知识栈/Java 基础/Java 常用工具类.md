```yaml
title: Java 常用工具类
author: samin
date: 2021-02-18
```

1. org.apache.commons.io.IOUtils

- closeQuietly()

- toString()

- copy()

- toByteArray()

- write()

- toInputStream()

- readLines()

- copyLarge()

- lineIterator()

- readFully()

2. org.apache.commons.io.FileUtils

- deleteDirectory()

- readFileToString()

- deleteQuietly()

- copyFile()

- writeStringToFile()

- forceMkdir()

- write()

- listFiles()

- copyDirectory()

- forceDelete()

3. org.apache.commons.lang.StringUtils

- isBlank()

- isNotBlank()

- isEmpty()

- isNotEmpty()

- equals()

- join()

- split()

- EMPTY

- trimToNull()

- replace()

4. org.apache.http.util.EntityUtils

- toString()

- consume()

- toByteArray()

- consumeQuietly()

- getContentCharSet()

5. org.apache.commons.lang3.StringUtils

- isBlank()

- isNotBlank()

- isEmpty()

- isNotEmpty()

- join()

- equals()

- split()

- EMPTY

- replace()

- capitalize()

6. org.apache.commons.io.FilenameUtils

- getExtension()

- getBaseName()

- getName()

- concat()

- removeExtension()

- normalize()

- wildcardMatch()

- separatorsToUnix()

- getFullPath()

- isExtension()

7. org.springframework.util.StringUtils

- hasText()

- hasLength()

- isEmpty()

- commaDelimitedListToStringArray()

- collectionToDelimitedString()

- replace()

- delimitedListToStringArray()

- uncapitalize()

- collectionToCommaDelimitedString()

- tokenizeToStringArray()

8. org.apache.commons.lang.ArrayUtils

- contains()

- addAll()

- clone()

- isEmpty()

- add()

- EMPTY_BYTE_ARRAY

- subarray()

- indexOf()

- isEquals()

- toObject()

9. org.apache.commons.lang.StringEscapeUtils

- escapeHtml()

- unescapeHtml()

- escapeXml()

- escapeSql()

- unescapeJava()

- escapeJava()

- escapeJavaScript()

- unescapeXml()

- unescapeJavaScript()

10. org.apache.http.client.utils.URLEncodedUtils

- format()

- parse()

11. org.apache.commons.codec.digest.DigestUtils

- md5Hex()

- shaHex()

- sha256Hex()

- sha1Hex()

- sha()

- md5()

- sha512Hex()

- sha1()

12. org.apache.commons.collections.CollectionUtils

- isEmpty()

- isNotEmpty()

- select()

- transform()

- filter()

- find()

- collect()

- forAllDo()

- addAll()

- isEqualCollection()

13. org.apache.commons.lang3.ArrayUtils

- contains()

- isEmpty()

- isNotEmpty()

- add()

- clone()

- addAll()

- subarray()

- indexOf()

- EMPTY_OBJECT_ARRAY

- EMPTY_STRING_ARRAY

14. org.apache.commons.beanutils.PropertyUtils

- getProperty()

- setProperty()

- getPropertyDescriptors()

- isReadable()

- copyProperties()

- getPropertyDescriptor()

- getSimpleProperty()

- isWriteable()

- setSimpleProperty()

- getPropertyType()

15. org.apache.commons.lang3.StringEscapeUtils

- unescapeHtml4()

- escapeHtml4()

- escapeXml()

- unescapeXml()

- escapeJava()

- escapeEcmaScript()

- unescapeJava()

- escapeJson()

- escapeXml10()

16. org.apache.commons.beanutils.BeanUtils

- copyProperties()

- getProperty()

- setProperty()

- describe()

- populate()

- copyProperty()

- cloneBean()  