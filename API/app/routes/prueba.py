from flask import Blueprint
from flask import request

prueba = Blueprint("prueba", import_name="prueba")

@prueba.get("/<Name>")
def pruebiña(Name: str):
    return f"hola, {Name}"

@prueba.post("/jajeo")
def jaj():
    quien = request.args.get("quien")
    cosa = request.data.decode("utf-8")
    return f"webo {quien} hizo {cosa}"