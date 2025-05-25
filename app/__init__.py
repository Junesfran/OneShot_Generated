from flask import Flask
from app.config.settings import user_db_uri, theStrange_db_uri
from flask_sqlalchemy import SQLAlchemy


app = Flask(__name__)

app.config["SQLALCHEMY_DATABASE_URI"] = user_db_uri
app.config["SQLALCHEMY_BINDS"] = {
    "the_strange": theStrange_db_uri
}

db = SQLAlchemy()
db.init_app(app)


from app.routes.test import test
app.register_blueprint(test)

from app.routes.user import user
app.register_blueprint(user)

from app.routes.manual import manual
app.register_blueprint(manual)

from app.routes.TheStrange.recursion import theStrange_recursion
app.register_blueprint(theStrange_recursion)