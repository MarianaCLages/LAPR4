# Client Answers

> **Question:**
> In document "brand name and reference." - Reference refers to the brand reference or product reference?
>
> **Answer:**
> The complete sentence is "Products basic information comprehends a unique internal code, a short and an extended description as well as a more technical description, a set of photos, a brand name and reference.".
>
>The intent was to express that a product has:
>
>* a brand name (e.g.: "Samsung", "Nokia", "Bic");
>* and the product reference set by the brand, which is an alphanumeric code (max. 23 chars).


> **Question:**
> In the application asked to be developed is it required to be implemented a way to manage users and warehouses?
>
> **Answer:** Regarding users administration the required development effort should be minimised in line with the following recommendation:
> "Regarding these features, it is foreseen that each team would have a great benefit by reutilizing the framework provided by the EAPLI course unit. As so, its adoption is recommended, but not mandatory." (cf. section 3.4).
> Regarding the warehouse management, for the prototype might consider a single one whose plant information is uploaded by a JSON file (cf. Use Case 3.2.1).

> **Question:**
> We would like to know if it will be needed to manage or create new product catalogs or if it's only needed to make searches into it.
>
> **Answer:**
> The "product catalog" corresponds to all products that can be ordered by customers.
> There is no need to manage distinct catalogs as usually happens with clothes where there is the  "Spring/Summer 22" catalog, then the  "Autumn/Winter 23" catalog, and so on..


> **Question:**
> Regarding to the creation of surveys, should they be created only after an clientOrder?
>
> **Answer:**
> There is no direct relationship between the use case to create orders and the one to create surveys.

> **Question:**
> The development team wonders whether in a product registration the attributes "photo", "internal code", "short description", "extended description", "technical description", "brand", "reference", "production code" are mandatory and how big they are?
>
> **Answer:**
> Some information regarding each of those attributes:
> - photo: it might be of any common format (e.g. png, jpeg, svg);
> - short description: not empty having 30 chars maximum;
> - extended description: not empty with a minimum of 20 chars and 100 chars maximum;
> - technical description: multiple lines of text, preferably with no limit or the biggest possible.
>- brand: not empty having 50 chars maximum;
>- reference: not empty having 23 chars maximum;
> - production code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.
> - internal code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.
>
> Mandatory attributes: internal code, short and extended description.
>
> In accordance with the specification document, other atributes might also be mandatory.

> **Question:**
> The development team wonders if stock validation is required when ordering a product?
>
> **Answer:**
> Within this prototype, stock validation is definitively out of scope.

> **Question:**
> The development team wonders if the clientOrder volume and weight should be a calculated based on the products entered in it?
>
> **Answer:**
> Yes, of course.
> Regarding volume, for now, adopt a very basic algorithm to compute the approximate overall volume

> **Question:**
> The development team asks what information do you consider necessary to look up on a past invoice/clientOrder?
>
> **Answer:**
> There are multiple report that might list orders.
> No matter which report, if orders are listed the "clientOrder id" is visible.

> **Question:**
> The team didn't understand what you meant by "myriad of reports", can you give an example with headers please?
>
> **Answer:**
> Past orders might be accessible through a myriad of reports (e.g.: eapli.base.customermanagement.domain.Customer past clientOrder in a time interval).
> If someone needs to access directly an clientOrder without previously requesting an orders' list/report, the clientOrder id is used.

> **Question:**
> The development team wonders where is the valid delivery point for an AGV clientOrder located? Is it at the dock itself?
>
> **Answer:**
> Yes! The AGV delivers the collected products on its dock.

> **Question:**
> May you please clarify what you pretend with the question type "Sorting Options"?
>
> **Answer:**
> It is a question type whose purpose is to sort/clientOrder a set of option usually from the most important one to the less important or vice-versa.
> An example:
>- Q1: Please clientOrder the following statements according to your standpoint. At the top, put the one you consider as most relevant and on the bottom the less relevant.
>- Op1: I appraise the origin (country) of the product.
>- Op2:  I appraise the use of natural ingredients in the product composition.
>- Op3: I appraise the price of the product.
>- Op4:  I appraise the quality of the product package.
   > As so, someone could sort the options as [Op3, Op2, Op4, Op1] while others might sort the options as [Op1, Op3, Op4, Op2], and so on.

> **Question:**
> May you please clarify the needed data to create a new User?
>
> **Answer:**
> Just the basic information: a name, an email and password.
> Yet, I believe it might help recall what I said before:
"Regarding users administration the required development effort should be minimised in line with the following recommendation:
"Regarding these features, it is foreseen that each team would have a great benefit by reutilizing the framework provided by the EAPLI course unit. As so, its adoption is recommended, but not mandatory." (cf. section 3.4).

> **Question:**
> May you please clarify how taxes are calculated for each product?
> We are aware that each country has a different tax rate for products, but is there a universal tax rate per country, are taxes divided into product categories or does each product have a completely different tax?
>
> **Answer:**
> During the prototype development, there is no need to spend efforts regarding products' tax rates and its computation. As stated in the specification document, regarding a product "by now, it is just worth considering the current list price with and without taxes."
Regarding a products clientOrder, it is similar. It is just worth computing/saving the "clientOrder total amount (before and after taxes)". Example:
>
>Product ID | Quantity | Unitary Price Before Taxes | Unitary Price After Taxes
>
> PRD.0001 | 2 | 10.00 | 12.30
>
> PRD.0002 | 1 | 5.00 | 6.15
>
>Total Order (before taxes): 25.00
>
>Total Order (after taxes): 30.75

> **Question:**
> While reading the project description and working around the Domain Model, the team came to the following questions:
>
>Q: Where does the Sales Clerk works?
>
>Q: In the project description there is the mention of the existence of Statistical Reports. Who are the System Users that are able to check on these Reports?
>
> **Answer:**
> Q: Where does the Sales Clerk works?
>
> At the organisation/company' offices. I don't think this is relevant.
>
> (S)he use the "Backoffice Application".
>
> Q: In the project description there is the mention of the existence of Statistical Reports. Who are the System Users that are able to check on these Reports?
> The sales manager.

> **Question:**
> What are the parameters, i.e. what does a payment confirmation consist of?
>
> **Answer:**
> It varies from one payment service to another. Generalising, consider it a text with 512 chars max.


> **Question:**
> The descriptions that are mentioned several times (short, technical and extended) always follow the same business rules, or change rules when dealing with other business terms, ie the description of a Section has the same rules as the description of a Product?
>
> **Answer:**
> The rules may change according to the business term context

> **Question:**
> What information will the system keep from employees?
>
> **Answer:**
> The one need to become system user only.


> **Question:**
> Does the customer (client) need to have an address when creating? Or initially this field may be empty and only need address when it requests a purchase.
>
> **Answer:**
> Regarding customers, the minimum required information is its name, a valid Value-Added Tax (VAT) identifier, an email address, and a phone number.

> **Question:**
> Can we conclude that if the AGV does not support the weight required for an Order, it automatically rejects this task?
>
> **Answer:**
> Yes! But, notice that regarding AGV Digital Twin the "automatically" is implemented by your team.


> **Question:**
> Could you please clarify what the product barcode and production code consists of?
>
> **Answer:**
> Product barcode follow universal standards as EAN13, UPC and so on. The purpose is to univocally identify a given product.
> The production code is used for integration with other external systems, such as a manufacturing system.

> **Question:**
> Is the barcode unique to each product unit or is it common to all units of that particular product?
>
> **Answer:**
> The product barcode is the same for all units of a given product.


> **Question:**
> When we create a survey, is there a default set of question predefined when creating it, or do we need to create the questions themselves one by one for every survey?
>
> **Answer:**
> No. All surveys are distinct. As so, each survey is defined from the scratch.

> **Question:**
> Do we only want to show the notification to the client, or do we also use it to get a certain statistic with it that demands it?
>
> **Answer:**
> Notifications are shown and used to ease the client to answer the survey.
> Statistics might be made, for instance, based on the number of notifications and the total amount of answers received.

> **Question:**
> Who is the responsibility of creating a survey?
>
> **Answer:**
> As stated in section 2.2 of the specifications' document, the responsibility is of the Sales Manager.

> **Question:**
> We have the knowledge that the clerk can create a clientOrder on behalf of a given costumer and with that the system might request additional information regarding the source clientOrder information, on the project descriptions gives the example of phone number, email and others.
> What additional information do you need more?
>
> **Answer:**
> Regarding that topic, it is import to recall the following:
>
>"At least for some organizations, it is also important that the system allows sales department clerks to create orders on behalf of a given customer. Thus, the system should collect the required information to distinguish between those orders registered directly by the customer and the ones registered by a clerk. In the latter case, due trackability concerns, the system should be able (at least) to identify the respective clerk."
>
>Despite identifying the clerk registering the clientOrder, it is important to register (i) the source channel (e.g.: phone, email, meeting, etc...), (ii) the date/time when such interaction happen and (iii) optionally add some comment.

> **Question:**
> Should the shipment method price be managed later on by the company or it's a constant value that doesn't require future management?
>
> **Answer:**
> Yes, the shipment price varies from one method to another. Thus, an option to manage such information will be required. But, such development is currently out of scope.

> **Question:**
> Should the system be able to add more payment methods later on or are those payments always automatically received and have no verification needed?
>
> **Answer:**
> Yes. Multiple  payment methods can be added/supported. Currently, the team just need to be aware that is an extension point and prepare the system for that.

> **Question:**
> What would you like to happen to a product if it's category is erased from the system?
>
> **Answer:**
> In such cases, the category should not be erased.

> **Question:**
> What do you understand about configuring the AGV's available on the warehouse. What changes the warehouse employee can do in his specifics and actions/taks?
>
> **Answer:**
> Within this context, "configuring the AGVs available on the warehouse" means that the Warehouse eapli.base.employeemanagement.domain.Employee needs to specify which are the AGV operating in the warehouse and, therefore, define some basic information regarding each AGV.
>
>Please, read carefully the specifications' document to find ou which information is need. An highlight is provided below.
>
>"The AGVs operating on the warehouse, its characteristics (e.g.: identifier, short description, model, and maximum weight it can carry) and its base location (i.e., the AGV dock). In addition, it is necessary to know the AGV status regarding its autonomy (e.g.: 2 hours left) and current task (e.g.: free, charging, occupied serving a given clientOrder)." (cf. Specifications' document)

> **Question:**
> Will a graphical interface to the system be necessary? To show the product catalog and that sort of thing?
>
> **Answer:**
> By default, a console-based user interface is required.
>
> Some features will require a more advance UI, namely a web-based UI (e.g.: dashboards). In such cases, it will be explicitly stated

> **Question:**
> A single AGV dock, no matter how large, can only be the base for one AGV robot?
>
> **Answer:**
> Yes. That is correct.

> **Question:**
> Regarding the product search, is there a field you want to use to filter the data and any desired data presentation clientOrder? And do you want to see all the fields or just a simplified summary?
>
> **Answer:**
> Commonly fields used to filter products are:
>
>- Category
>- Description (any of the available descriptions)
>- Brand
>
> User should select/specify a data presentation clientOrder. This applies to any similar US.
>
>At least the product' code, short description, brand, category and unit price should be presented.
>
>More details can be presented for a given/selected product at user request.

> **Question:**
> Can the Product be a part of a Super Category and a Category or will the Product be a part of a Category and that Category is a part of the Super Category?
>
> **Answer:**
> As stated in the specifications' document:
>
>"By simplicity, a category consists only of an alphanumeric code, and a description. Each product belongs mandatorily to a single category."
>
>As so, and contrary to what is suggested in your question, there is no hierarchy between categories.

> **Question:**
> Are the generated Reports going to be used for statistical purposes only or are they intended to be kept?
>
> **Answer:**
> Reports are generated over data available and/or persisted in the developing system.
>
>There is no need to keep/persist output reports since, at any time, reports can be (re)genera

> **Question:**
> Is it required that the system keep an history of the clientOrder state? For example, if an clientOrder changes from "payment pending" to "to be prepared", are we required to keep the date of this change for future query?
>
> **Answer:**
> Keeping clientOrder state history, as suggested, is an interesting feature that will be highly appreciated/valued.


> **Question:**
> Does a warehouse always have the same plant or can it have different plants in the future?
>
> **Answer:**
> Each warehouse has its own plant and, therefore, plants might vary from one warehouse to another.
>
>However, any warehouse plant is described by a JSON file according to the data structure described in section 5.2 of the specifications' document.
>
>On US2001, any JSON file meeting such data structure must be supported.
