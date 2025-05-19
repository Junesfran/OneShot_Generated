from flask import Flask

app = Flask(__name__)

from app.routes.test import test
app.register_blueprint(test)

from app.routes.prueba import prueba
app.register_blueprint(prueba)