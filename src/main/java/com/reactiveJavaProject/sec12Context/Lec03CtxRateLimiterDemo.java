package com.reactiveJavaProject.sec12Context;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec12Context.helper.BookService;
import com.reactiveJavaProject.sec12Context.helper.UserService;
import reactor.util.context.Context;

public class Lec03CtxRateLimiterDemo {
    public static void main(String[] args) {
/*implement a book service that will provide amount a books based on the user name
 that is connected with the user profile standard or prime */

        BookService.getBook()
                .repeat(3)
                .contextWrite(UserService.userCategoryContext())
//                .contextWrite(Context.of("user", "mike"))
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

/*   1. we set context =  "Content{user=mike}"
     2. UserService.userCategoryContext()
     3. user = ctx.get("user").toString() = "mike"
     4. category = MAP.get(user) = "prime"
     5. ctx.put("category", category) = "Content{"category","prime"}
     ---------------------------------------------------------------------------
     6. BookService.getBook()
            .repeat(3).contextWrite(Context.of("category","prime")
     7. in getBook() as first will execute .contextWrite(rateLimiterContext());
     8. rateLimiterContext() will be executed with Content{"category","prime"}
     9. category = ctx.get("category").toString() = "prime"
    ---------------------------------------------------------------------------
    10. attempts = map.get("prime") = 3
    11. (attempts > 0)= true --> map.put("prime",2)
    ---------------------------------------------------------------------------
    12. ctx.put("allow", true) = "Content{"allow","true"}
    13. coming back on line 23 in BookService ctx.get("allow") = true
    14. line 24 will return a random book
    ---------------------------------------------------------------------------
    15. we come back in this class line 13 and repeat another 3 times everything

    16. steps from 6 to 9
    17. attempts = map.get("prime") = 2
    18. (attempts > 0) --> map.put("prime",1)
    19. steps from 12 to 14
    ---------------------------------------------------------------------------
    20. we come back in this class line 13 and repeat another 2 times everything

    21. steps from 6 to 9
    22. attempts = map.get(category) = 1
    23. (attempts > 0) --> map.put("prime",1)
    24. steps from 12 to 14
    ---------------------------------------------------------------------------
    25. we come back in this class line 13 and repeat another 1 time everything

    26. steps from 6 to 9
    27. attempts = map.get(category) = 1
    28. (attempts > 0) == false --> ctx.put("allow", false) = "Content{"allow","false"}
    29. coming back on line 23 in BookService ctx.get("allow") = false
    30. line 26 will return error "not allowed"

    Final result printed will be:

    Received : <Random book1>
    Received : <Random book2>
    Received : <Random book3>
    ERROR    : "not allowed"

    if we change "mike" with "sam" the category will change to "standard" we'll have:

    Received : <Random book1>
    Received : <Random book2>
    ERROR    : "not allowed"

*/
}
