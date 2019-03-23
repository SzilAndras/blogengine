# blogengine

Összes kategória:
http://localhost:8080/rest/categories/all

Új kategória hozzáadása:
http://localhost:8080/rest/categories/addCategory
body: {"categoryId": int, "name": str}

Kategória törlése:
http://localhost:8080/rest/categories/deleteById={id}

Kategóriához tartozó bejegyzések:
http://localhost:8080/rest/categories/getPostsByCategoryId={catId}

Összes bejegyzés:
http://localhost:8080/rest/posts/all

Új bejegyzés:
http://localhost:8080/rest/posts/newPost
body: {"postId": int, "title": str, "content": str}

Bejegyzés frissítése:
http://localhost:8080/rest/posts/updatePost
body: {"postId": int, "title": str, "content": str, "additionDate": date}

Bejegyzés törlése:
http://localhost:8080/rest/posts/deletePostById={postId}

Bejegyzések lekérése kategória név alapján(tartalmazás is):
http://localhost:8080/rest/posts/getPostsByCategoriesNameContaining={name}

Kategória és bejegyzés összekapcsolása:
http://localhost:8080/rest/posts/addCategoryById={catId}ToPostId={postId}

Kategória és bejegyzés szétválasztása:
http://localhost:8080/rest/posts/removeCategoryById={catId}fromPostId={postId}

