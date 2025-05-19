from flask import Blueprint

test = Blueprint("test", import_name="test", url_prefix="/test")

@test.get("/")
def pruebiña():
    return "puto el que lo lea xd"

@test.get("/webos")
def webos():
    return ["josue"]