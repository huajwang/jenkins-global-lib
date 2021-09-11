import base64
import os
import requests
import re
import logging
import argparse


def setup_params():
    parser = argparse.ArgumentParser(description='Main python to run all scripts')
    parser.add_argument("--find", action="store", help='find build', default=None)
    parser.add_argument("--download", action="store", help='download build with <url>', default=None)
    return parser.parse_args()


def run(find=None, download=None):
    if find:
        print(f"find {find}")
    if download:
        print(f"download {download}")


if __name__ == "__main__":
    logging.basicConfig(format='%(name)s %(levelname)s:%(message)s', level=logging.INFO)
    args = setup_params()
    logging.info(f"args = {args}")
    run(args.find, args.download)
