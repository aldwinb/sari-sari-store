#!/usr/bin/env python

from subscriber import msgbus
import configparser

def on_message(channel, method_frame, header_frame, body):
    print(method_frame.delivery_tag)
    print(body)
    print()
    channel.basic_ack(delivery_tag=method_frame.delivery_tag)


def main():
    config = configparser.ConfigParser()
    config.read('app.ini')

    client = msgbus.RabbitMqClient(config['rabbitmq']['host'])
    opts = msgbus.RabbitMqChannelOptions(config['rabbitmq']['host'],
                                         config['rabbitmq']['exchange'],
                                         config['rabbitmq']['topics'],
                                         config['rabbitmq']['queue'])

    channel = client.create_channel(opts)
    channel.basic_consume(on_message, 'test')
    try:
        channel.start_consuming()
    except KeyboardInterrupt:
        channel.stop_consuming()
    channel.connection.close()
    channel.close()


if __name__ == '__main__':
    main()