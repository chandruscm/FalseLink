# WhatTheLink

![](https://github.com/chandruscm/WhatTheLink/blob/master/images/header.png)

Everyday we encounter numerous web-links across various apps ranging from social media to email, most of which are shortened URLs, making it impossible to know where they might lead to. Some malicious web-links are even disguised with legitimate website names to trick the average user. The internet is indeed a boon. But it is fair to say that for the average user, the internet is a black box and bad actors take advantage of this, whether it be stealing personal information, mining crypto-currencies or promoting illicit content.

So how do we provide a safe experience on the web? I believe that it should start even before the user visits a website. Say the user receives a suspicious web-link on some app. Upon clicking this link, we need to verify the content and credibility of the website it leads to and inform the user before he opens it in a browser.

To achieve this, I propose 2 stages of verification.
1. URL verification - Detect redirecting URLs, detect phishing URLs using simple decision tree based approaches.
2. Content verification - Extract and determine the content of a website using Text and Image classification. (adult content, illegal betting websites, advertising spams etc.)
