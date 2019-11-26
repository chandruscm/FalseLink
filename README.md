# FalseLink

![](https://github.com/chandruscm/WhatTheLink/blob/master/images/header.png)

Everyday we encounter numerous web-links across various apps ranging from social media to email, most of which are shortened URLs, making it impossible to know where they might lead to. Some malicious web-links are even disguised with legitimate website names to trick the average user. The internet is indeed a boon. But it is fair to say that for the average user, the internet is a black box and bad actors take advantage of this, whether it be stealing personal information, mining crypto-currencies or promoting illicit content.

A safe web experience should begin even before the user visits a website. When the user tries to open a suspicious web-link on any app, FalseLink intercepts to verify the content and credibility of the website it leads to and informs the user before he opens it in a browser.

To achieve this, I propose 2 stages of verification:
1. Malicious URL detection - Detect phishing URLs and repeated redirecting URLs
2. Web content classification - Extract and determine the content of a website using NLP, Text/Image classification. (adult content, illegal betting websites, advertising spams etc.)

Existing solutions either requires the user to continually share their browsing information with 3rd parties or simply black-lists websites that are considered to be known bad-actors. Leveraging on-device machine learning enables us to perform low-latency-real-time verification on the device without ever having to share the user’s browsing information.
