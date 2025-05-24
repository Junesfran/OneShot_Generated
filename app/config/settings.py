from dotenv import load_dotenv
import base64
import os

load_dotenv()

user_db_uri = os.getenv("USER_DATABASE_URI")
theStrange_db_uri = os.getenv("THESTRANGE_DATABASE_URI")

jwt_key = os.getenv("JWT_KEY")
private_key = os.getenv("PRIVATE_KEY").encode("utf-8")