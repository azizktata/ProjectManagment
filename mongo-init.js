db = db.getSiblingDB('admin');
// move to the admin db - always created in Mongo
db.auth("rootuser", "rootpass");
db = db.getSiblingDB('pdsdb');
db.createUser(
        {
            user: "aziz",
            pwd: "system",
            roles: [
                {
                    role: "readWrite",
                    db: "pdsdb"
                }
            ]
        }
);