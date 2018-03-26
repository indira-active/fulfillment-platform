import sys

if sys.argv[1] == "1":
    print("Exiting with error")
    sys.exit(1)
print("Exiting without error")
sys.exit(0)
